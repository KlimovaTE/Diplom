package data;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    public static class AuthInfo {

        public static String createJSON(CardInfo info) {
            return "{\n" +
                    "  \"number\": \"" + info.getNumber() + "\",\n" +
                    "  \"year\": \"" + info.getMonth() + "\",\n" +
                    "  \"month\": \"" + info.getYear() + "\",\n" +
                    "  \"holder\": \"" + info.getHolder() + "\",\n" +
                    "  \"cvc\": \"" + info.getCvc() + "\"\n" +
                    "}";
        }
    }

    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getValidMonthRandom() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String month = sdf.format(faker.date().birthday());
        return month;
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidOwnerName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidCVC() {
        int n = (int) (Math.random() * 999 + 1);
        return String.format("%03d", n);
    }

    public static String getOneDigit() {
        int element = (int) (Math.random() * 10);
        return Integer.toString(element);
    }

    public static String getOneNumberFrom13to99() {
        int element = (int) (Math.random() * 87 + 13);
        return Integer.toString(element);
    }

    public static String getLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getThisYearPlus6() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getThisYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getThisMonthMinus1() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInvalidOwnerOnlyOneName() {
        return faker.name().firstName();
    }

    public static String getInvalidOwnerThreeName() {
        return faker.name().firstName() + " " + faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getInvalidOwnerNameLength65() {
        String firsName = RandomStringUtils.randomAlphabetic(30);
        String secondName = RandomStringUtils.randomAlphabetic(34);
        return firsName + " " + secondName;
    }
    public static String getInvalidOwnerNameWithNumber() {
        return faker.name().firstName()+ getOneNumberFrom13to99()+" " + faker.name().lastName()+getOneNumberFrom13to99();
    }
    public static String getInvalidOwnerNameWithCyrillic() {
Faker fakerRU = new Faker(new Locale("ru"));
        return faker.name().firstName()+ " " + fakerRU.name().lastName();
    }
}
