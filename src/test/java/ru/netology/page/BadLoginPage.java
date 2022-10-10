package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BadLoginPage {
    private SelenideElement heading = $("[data-test-id=\"error-notification\"]");

    public BadLoginPage() {
        heading.shouldBe(visible);
    }
}
