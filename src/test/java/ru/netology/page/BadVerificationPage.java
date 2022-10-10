package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BadVerificationPage {
    private SelenideElement heading = $("[data-test-id=\"error-notification\"]");

    public BadVerificationPage() {
        heading.shouldBe(visible);
    }
}
