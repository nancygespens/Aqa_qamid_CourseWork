package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.ChoiceOfPaymentVariantPage;
import ru.netology.pages.CreditPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.*;

public class SQLTests {
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
        SQLHelper.cleanDatabase();
    }

    @Test
    void successfulPayment() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        make.successfulPayment(DataHelper.getValidInfoForPayByCard());
        Assertions.assertEquals("APPROVED", SQLHelper.getOperationStatusOfPayment());
    }

    @Test
    void declinedPayment() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getDeclinedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.declinedPaymentWithoutCheck(info);
        Assertions.assertEquals("DECLINED", SQLHelper.getOperationStatusOfPayment());
    }

    @Test
    void randomCardValidFormatPayment() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getRandomCardNumberValidFormat(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.declinedPaymentWithoutCheck(info);
        Assertions.assertEquals(null, SQLHelper.getOperationStatusOfPayment());
    }

    @Test
    void successfulCredit() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCredit();
        CreditPage make = new CreditPage();
        make.makeSuccessfulPayment(DataHelper.getValidInfoForPayByCard());
        Assertions.assertEquals("APPROVED", SQLHelper.getOperationStatusOfCredit());
    }

    @Test
    void declinedCredit() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCredit();
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getDeclinedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.declinedPaymentWithoutCheck(info);
        Assertions.assertEquals("DECLINED", SQLHelper.getOperationStatusOfCredit());
    }

    @Test
    void randomCardValidDeclinedCredit() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCredit();
        CreditPage make = new CreditPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getRandomCardNumberValidFormat(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.declinedPaymentWithoutCheck(info);
        Assertions.assertEquals(null, SQLHelper.getOperationStatusOfCredit());
    }
}