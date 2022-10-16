package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement pass = $("[data-test-id=password] input");
    private SelenideElement button = $("[data-test-id=action-login]");

    private SelenideElement notification = $("[data-test-id=error-notification] div.notification__content");

    public void inputData(DataHelper.AuthInfo info){
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        button.click();
    }
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        inputData(info);
        return new VerificationPage();
    }

    public String validLoginBadPass(DataHelper.AuthInfo info) {
        inputData(info);
        notification.shouldBe(Condition.visible, Duration.ofSeconds(15));
        return notification.text();
    }

    public void cleanLoginPage () {
        login.doubleClick().sendKeys(Keys.DELETE);
        pass.doubleClick().sendKeys(Keys.DELETE);
    }
}