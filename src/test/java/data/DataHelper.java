package data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
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
        int n=(int)(Math.random()*999+1);
        return String.format("%03d", n);
    }

}
