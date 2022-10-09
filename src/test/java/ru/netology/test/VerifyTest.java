package ru.netology.test;

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

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerifyTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

//    @AfterEach
//    void cleanAuth(){
//
//    }
//
//    @AfterAll
//    void cleanBase(){
//
//    }

    @Test
    void shouldViewDashboard() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var login = authInfo.getLogin();
        var loginId = DBHelper.getUserId(login);
        var verificationCode = DBHelper.getVerificationCode(loginId);

        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("http://localhost:9999/dashboard", currentUrl);
    }

}
