package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

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


    private SelenideElement heading = $(byText("Оплата по карте")).parent().$(".heading");
//    private SelenideElement heading = $(".heading").find(byText("Оплата по карте"));
//    private SelenideElement forPayButton = $("button").find(String.valueOf(text("Купить")));
    // private SelenideElement cardNumberField = $$(".input__top").find(text("Номер карты"));
//    private SelenideElement cardNumberField = $x("//*[@id='root']/div/form/fieldset/div[1]/span/span/span[1]");
//    private SelenideElement cardNumberField = $("input__inner").find(String.valueOf(text("Номер карты")));
//    private SelenideElement cardNumberField = $("input").shouldHave(text("Номер карты"));
//    private SelenideElement cardNumberField = $(byText("Номер карты"));

    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    //        private SelenideElement cardNumberField = $(".input").find(String.valueOf(exactText("Номер карты")));
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
//    private SelenideElement monthField = $(byText("Месяц"));
    //    private SelenideElement monthField = $(".form-field").find(String.valueOf(text("Месяц")));

    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
//    private SelenideElement yearField = $(byText("Год"));
    //    private SelenideElement yearField = $(".form-field").find(String.valueOf(text("Год")));

    private SelenideElement cardOwnerField = $(byText("Владелец")).parent().$(".input__control");
//    private SelenideElement cardOwnerField = $(byText("Владелец"));
    //    private SelenideElement cardOwnerField = $(".form-field").find(String.valueOf(text("Владелец")));

    private SelenideElement cvcCodeField = $(byText("CVC/CVV")).parent().$(".input__control");
    //    private SelenideElement cvcCodeField = $(byText("CVC/CVV"));
    //    private SelenideElement cvcCodeField = $(".form-field").find(String.valueOf(text("CVC/CVV")));
    private SelenideElement payButton = $(byText("Продолжить"));
    //    private SelenideElement payButton = $("button").find(String.valueOf(text("Продолжить")));

    private SelenideElement successNotification = $(byText(successMsg)).parent().$(".notification__content");
    //    private SelenideElement successNotification = $(byText(successMsg));
    //    private SelenideElement successNotification = $x(successMsg);
    private SelenideElement bankFailureNotification = $(byText(bankFailureMsg));
    //    private SelenideElement bankFailureNotification = $x(bankFailureMsg);
    Integer validDuration = 10;

    public PaymentPage() {
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

    public PaymentPage makeSuccessfulPayment(DataHelper.InfoForPayByCard info) {
        successfulPayment(info);
        return new PaymentPage();
    }

    public PaymentPage makeDeclinedPayment(DataHelper.InfoForPayByCard info) {
        declinedPayment(info);
        return new PaymentPage();
    }

    public PaymentPage makeInvalidPayment(DataHelper.InfoForPayByCard info) {
        invalidPayment(info);
        return new PaymentPage();
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