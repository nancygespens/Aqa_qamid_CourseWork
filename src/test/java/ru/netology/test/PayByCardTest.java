package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.ChoiceOfPaymentVariantPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.*;

public class PayByCardTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
    }

    @Test
    void successfulPayment() {
        PaymentPage make = new PaymentPage();
        make.makeSuccessfulPayment(DataHelper.getValidInfoForPayByCard());
    }

    @Test
    void declinedPayment() { //падает, issue 1
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getDeclinedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makeDeclinedPayment(info);
    }

    @Test
    void emptyCardNumber() {
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getEmptyCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makeInvalidPayment(info);
    }

    @Test
    void lastMonth() {
        PaymentPage make = new PaymentPage();
        String year = DataHelper.getCurrentYear();
        if (Integer.valueOf(DataHelper.getCurrentMonth()) == 1) {
            year = String.valueOf((Integer.valueOf(DataHelper.getCurrentYear()) - 1));
        }
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getLastMonth(), year, DataHelper.getValidCardOwner(), DataHelper.getRandomCvcCode());
        make.makePaymentInvalidMonth(info);
    }

    @Test
    void emptyMonth() {
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getEmptyMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyMonth(info);
    }

    @Test
    void lastYear() {
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getLastYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidYear(info);
    }

    @Test
    void emptyYear() {
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getEmptyYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyYear(info);
    }

    @Test
    void cardOwnerPlusNumber() { //падает, issue 2
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getInvalidCardOwnerByNumber(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidCardOwner(info);
    }

    @Test
    void emptyCardOwner() {
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getEmptyCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyCardOwner(info);
    }

    @Test
    void invalidCvcCode() {
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getInvalidCvcCode());
        make.makePaymentInvalidCvcCode(info);
    }

    @Test
    void emptyCvcCode() { //падает, issue 5
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getEmptyCvcCode());
        make.makePaymentEmptyCvcCode(info);
    }
}