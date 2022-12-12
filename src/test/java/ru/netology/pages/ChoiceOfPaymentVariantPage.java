package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ChoiceOfPaymentVariantPage {
    private SelenideElement forPayByCardButton = $("button").shouldHave(text("Купить"));
    private SelenideElement forPayByCreditButton = $(byText("Купить в кредит")).parent().$(".button__text");

    public ChoiceOfPaymentVariantPage payByCard() {
        forPayByCardButton.click();
        return new ChoiceOfPaymentVariantPage();
    }

    public ChoiceOfPaymentVariantPage payByCredit() {
        forPayByCreditButton.click();
        return new ChoiceOfPaymentVariantPage();
    }
}