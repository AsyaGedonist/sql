package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerifyTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @AfterEach
    void cleanAuth(){
    DBHelper.cleanAuth();
    }

    @AfterAll
    static void cleanBase(){
    DBHelper.cleanBase();
    }

    @Test
    void shouldViewDashboard() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var login = authInfo.getLogin();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DBHelper.getVerificationCode(login);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void fakeVerificationCode() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var login = authInfo.getLogin();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DBHelper.getVerificationCode(login);
        var fakeVerificationCode = DBHelper.generateFakeVerificationCode(verificationCode);
        var notification = verificationPage.inValidVerify(fakeVerificationCode);

        assertEquals("Ошибка! Неверно указан код! Попробуйте ещё раз.", notification);
    }

    @Test
    void badPassword() {
        var loginPage = new LoginPage();
        var badAuthInfo = DataHelper.getBadAuthInfo();
        var notification = loginPage.validLoginBadPass(badAuthInfo);

        assertEquals("Ошибка! Неверно указан логин или пароль", notification);
    }

    @Test
    void badPasswordTreeTimes() {
        var loginPage = new LoginPage();
        var badAuthInfo = DataHelper.getBadAuthInfo();

        loginPage.validLoginBadPass(badAuthInfo);
        loginPage.cleanLoginPage();
        loginPage.validLoginBadPass(badAuthInfo);
        loginPage.cleanLoginPage();
        loginPage.validLoginBadPass(badAuthInfo);

        var status = DBHelper.getStatus(badAuthInfo.getLogin());
        assertEquals("blocked", status);
    }

}
