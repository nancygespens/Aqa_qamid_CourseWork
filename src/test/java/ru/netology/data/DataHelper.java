package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InfoForPayByCard {
        String cardNumber;
        String month;
        String year;
        String cardOwner;
        String cvcCode;
    }

    public static String getApprovedCardNumber() {
        return "1111 2222 3333 4444";
    }

    public static String getDeclinedCardNumber() {
        return "5555 6666 7777 8888";
    }

    public static String getRandomCardNumberValidFormat() {
        return faker.business().creditCardNumber();
    }

    public static String getEmptyCardNumber() {
        return "";
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastMonth() {
        return Integer.toString(Integer.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("MM"))) - 1);
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLastYear() {
        return Integer.toString(Integer.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yy"))) - 1);
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getValidCardOwner() {
        return faker.name().fullName();
    }

    public static String getCardOwnerSpecSymbol() {
        return faker.name().fullName() + "=-+!@?.,";
    }

    public static String getInvalidCardOwnerByNumber() {
        return faker.name().fullName() + faker.business().creditCardNumber();
    }

    public static String getInvalidCardOwnerByRus() {
        Faker fakerOnRus = new Faker(new Locale("ru"));
        return fakerOnRus.name().fullName();
    }

    public static String getEmptyCardOwner() {
        return "";
    }

    public static String getRandomCvcCode() {
        return faker.numerify("###");
    }

    public static String getInvalidCvcCode() {
        return faker.numerify("##");
    }

    public static String getEmptyCvcCode() {
        return "";
    }

    public static InfoForPayByCard getValidInfoForPayByCard() {
        return new InfoForPayByCard(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), getValidCardOwner(), getRandomCvcCode());
    }

    public static String createJSON(InfoForPayByCard info) {
        return "{\n" +
                "  \"number\": \"" + info.getCardNumber() + "\",\n" +
                "  \"year\": \"" + info.getMonth() + "\",\n" +
                "  \"month\": \"" + info.getYear() + "\",\n" +
                "  \"holder\": \"" + info.getCardOwner() + "\",\n" +
                "  \"cvc\": \"" + info.getCvcCode() + "\"\n" +
                "}";
    }
}