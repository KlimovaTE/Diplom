package data;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static class AuthInfo {

        public static String createBodyPaymentRequest(CardInfo info) {
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

//    public static String getURL() {
//        return "http://localhost:8080";
//    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getMonthWithShift(int monthShift) {
        return LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYearWithShift(int yearShift) {
        return LocalDate.now().plusYears(yearShift).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidOwnerName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidCVC() {
        return getNumeric(3);
    }

    public static String getNumeric(int length) {
        return faker.number().digits(length);
    }

    public static String getOneNumberFrom13to99() {
        int element = (int) (Math.random() * 87 + 13);
        return Integer.toString(element);
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
        return faker.name().firstName() + getOneNumberFrom13to99() + " " + faker.name().lastName() + getOneNumberFrom13to99();
    }

    public static String getInvalidOwnerNameWithCyrillic() {
        Faker fakerRU = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + fakerRU.name().lastName();
    }
}
