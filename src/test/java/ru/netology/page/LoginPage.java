package ru.netology.page;

import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

//VerificationPage    public BadLoginPage validLoginBadPass(DataHelper.AuthInfo info) {
//        $("[data-test-id=login] input").setValue(info.getLogin());
//        $("[data-test-id=password] input").setValue(info.getPassword());
//        $("[data-test-id=action-login]").click();
//        return new BadLoginPage();
//    }

    public void cleanLoginPage () {
        $("[data-test-id=login] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=password] input").doubleClick().sendKeys(Keys.DELETE);
    }
}