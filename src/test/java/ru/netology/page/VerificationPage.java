package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(String code) {
        codeField.setValue(code);
        verifyButton.click();
        return new DashboardPage();
    }

    public BadVerificationPage invalidVerify(String code) {
        codeField.setValue(code);
        verifyButton.click();
        return new BadVerificationPage();
    }
}