package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.ChoiceOfPaymentVariantPage;
import ru.netology.pages.CreditPage;

import static com.codeborne.selenide.Selenide.*;

public class PayByCreditTest {
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
        choiceOfPaymentVariantPage.payByCredit();
    }

    @Test
    void successfulPayment() {
        CreditPage make = new CreditPage();
        make.makeSuccessfulPayment(DataHelper.getValidInfoForPayByCard());
    }

    @Test
    void declinedPayment() { //падает, issue 1
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getDeclinedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makeDeclinedPayment(info);
    }

    @Test
    void emptyCardNumber() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getEmptyCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makeInvalidPayment(info);
    }

    @Test
    void lastMonth() {
        CreditPage make = new CreditPage();
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
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getEmptyMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyMonth(info);
    }

    @Test
    void lastYear() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getLastYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidYear(info);
    }

    @Test
    void emptyYear() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getEmptyYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyYear(info);
    }

    @Test
    void cardOwnerPlusSpecSymbol() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getCardOwnerSpecSymbol(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidCardOwner(info);
    }

    @Test
    void cardOwnerPlusNumber() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getInvalidCardOwnerByNumber(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidCardOwner(info);
    }

    @Test
    void cardOwnerByRus() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getInvalidCardOwnerByRus(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidCardOwner(info);
    }

    @Test
    void emptyCardOwner() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getEmptyCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyCardOwner(info);
    }

    @Test
    void invalidCvcCode() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getInvalidCvcCode());
        make.makePaymentInvalidCvcCode(info);
    }

    @Test
    void emptyCvcCode() {
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getEmptyCvcCode());
        make.makePaymentEmptyCvcCode(info);
    }
}