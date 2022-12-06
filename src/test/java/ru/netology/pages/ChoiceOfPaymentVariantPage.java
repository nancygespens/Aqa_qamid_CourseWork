package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ChoiceOfPaymentVariantPage {
    private SelenideElement forPayByCardButton = $("button").shouldHave(text("Купить"));
    private SelenideElement forPayByCreditButton = $x("Купить в кредит");

    public ChoiceOfPaymentVariantPage payByCard() {
        forPayByCardButton.click();
        return new ChoiceOfPaymentVariantPage();
    }

    public ChoiceOfPaymentVariantPage payByCredit() {
        forPayByCreditButton.click();
        return new ChoiceOfPaymentVariantPage();
    }
}