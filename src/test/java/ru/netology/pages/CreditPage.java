package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CreditPage {

    private final String successMsg = "Операция одобрена Банком.";
    private final String bankFailureMsg = "Ошибка! Банк отказал в проведении операции.";
    private final String invalidFormatError = "Неверный формат";
    private final String monthError = "Неверно указан срок действия карты";
    private final String monthEmptyError = "Неверный формат";
    private final String yearError = "Истёк срок действия карты";
    private final String yearEmptyError = "Неверный формат";
    private final String cardOwnerError = "Неверный формат";
    private final String cardOwnerEmptyError = "Поле обязательно для заполнения";
    private final String cvcCodeError = "Неверный формат";
    private final String cvcCodeEmptyError = "Поле обязательно для заполнения";


    private SelenideElement heading = $(byText("Кредит по данным карты")).parent().$(".heading");
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement cardOwnerField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcCodeField = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement payButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $(byText(successMsg)).parent().$(".notification__content");
    private SelenideElement bankFailureNotification = $(byText(bankFailureMsg));
    Integer validDuration = 12;

    public CreditPage() {
        heading.shouldBe(visible);
    }

    private SelenideElement errorNotificationCardNumber = $(byText(invalidFormatError)).parent().$(".input__sub");
    private SelenideElement errorNotificationMonth = $(byText(monthError)).parent().$(".input__sub");
    private SelenideElement errorNotificationEmptyMonth = $(byText(monthEmptyError)).parent().$(".input__sub");
    private SelenideElement errorNotificationYear = $(byText(yearError)).parent().$(".input__sub");
    private SelenideElement errorNotificationEmptyYear = $(byText(yearEmptyError)).parent().$(".input__sub");
    private SelenideElement errorNotificationCardOwner = $(byText(cardOwnerError)).parent().$(".input__sub");
    private SelenideElement errorNotificationEmptyCardOwner = $(byText(cardOwnerEmptyError)).parent().$(".input__sub");
    private SelenideElement errorNotificationCvcCode = $(byText(cvcCodeError)).parent().$(".input__sub");
    private SelenideElement errorNotificationEmptyCvcCode = $(byText(cvcCodeEmptyError)).parent().$(".input__sub");

    public CreditPage makeSuccessfulPayment(DataHelper.InfoForPayByCard info) {
        successfulPayment(info);
        return new CreditPage();
    }

    public CreditPage makeDeclinedPayment(DataHelper.InfoForPayByCard info) {
        declinedPayment(info);
        return new CreditPage();
    }

    public CreditPage makeInvalidPayment(DataHelper.InfoForPayByCard info) {
        invalidPayment(info);
        return new CreditPage();
    }

    public void successfulPayment(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        successNotification.shouldBe(visible, Duration.ofSeconds(validDuration));
    }

    public void declinedPayment(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        bankFailureNotification.shouldBe(visible, Duration.ofSeconds(validDuration));
        successNotification.shouldNotBe(visible);
    }

    public void declinedPaymentWithoutCheck(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        sleep(validDuration * 1000);
    }

    public void invalidPayment(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationCardNumber.shouldBe(visible);
    }

    public void makePaymentInvalidMonth(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationMonth.shouldBe(visible);
    }

    public void makePaymentEmptyMonth(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationEmptyMonth.shouldBe(visible);
    }

    public void makePaymentInvalidYear(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationYear.shouldBe(visible);
    }

    public void makePaymentEmptyYear(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationEmptyYear.shouldBe(visible);
    }

    public void makePaymentInvalidCardOwner(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationCardOwner.shouldBe(visible);
    }

    public void makePaymentEmptyCardOwner(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationEmptyCardOwner.shouldBe(visible);
    }

    public void makePaymentInvalidCvcCode(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationCvcCode.shouldBe(visible);
    }

    public void makePaymentEmptyCvcCode(DataHelper.InfoForPayByCard info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        errorNotificationEmptyCvcCode.shouldBe(visible);
        errorNotificationCvcCode.shouldNotBe(visible);
    }
}