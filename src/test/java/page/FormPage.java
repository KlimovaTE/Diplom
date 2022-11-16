package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Data;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data

public class FormPage {
    private static SelenideElement cardField = $$("input").get(0);
    public static SelenideElement monthField = $$("input").get(1);
    public static SelenideElement yearField = $$("input").get(2);
    public static SelenideElement ownerField = $$("input").get(3);
    public static SelenideElement cvcField = $$("input").get(4);
    public static SelenideElement proceedButton = $(".form-field .button");
    private static SelenideElement textSuccess = $(".notification_status_ok");
    private static SelenideElement textError = $(".notification_status_error");
    private static SelenideElement textErrorInvalidFormat = $(".input__sub");

    public static SelenideElement paySuccess() {
        return textSuccess.shouldHave(text("Операция одобрена Банком."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public static SelenideElement payError() {
        return textError.shouldHave(text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public static SelenideElement errorInvalidFormat() {
        return textErrorInvalidFormat.shouldHave(text("Неверный формат"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public static SelenideElement errorInvalidCardExpirationDate() {
        return textErrorInvalidFormat.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public static SelenideElement errorExpiredCard() {
        return textErrorInvalidFormat.shouldHave(text("Истёк срок действия карты"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public static void setCard(String cardNumber) {
        cardField.setValue(cardNumber);
    }

    public static void setMonth(String month) {
        monthField.setValue(month);
    }

    public static void setYear(String year) {
        yearField.setValue(year);
    }

    public static void setOwnerName(String ownerName) {
        ownerField.setValue(ownerName);
    }

    public static void setCVC(String cvc) {
        cvcField.setValue(cvc);
    }

    public static void setMonth2(String month) {
        monthField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        monthField.setValue(month);
    }

    public static void setYear2(String month) {
        yearField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        yearField.setValue(month);
    }

    public static void setOwnerName2(String ownerName) {
        ownerField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        ownerField.setValue(ownerName);
    }
    public static void setCVC2(String cvc) {
        cvcField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        cvcField.setValue(cvc);
    }

    public static void setValidApprovedCard() {
        FormPage.setCard(DataHelper.getApprovedCardNumber());
        FormPage.setMonth(DataHelper.getValidMonthRandom());
        FormPage.setYear(DataHelper.getNextYear());
        FormPage.setOwnerName(DataHelper.getValidOwnerName());
        FormPage.setCVC(DataHelper.getValidCVC());
    }

    public static void setValidDeclinedCard() {
        FormPage.setCard(DataHelper.getDeclinedCardNumber());
        FormPage.setMonth(DataHelper.getValidMonthRandom());
        FormPage.setYear(DataHelper.getNextYear());
        FormPage.setOwnerName(DataHelper.getValidOwnerName());
        FormPage.setCVC(DataHelper.getValidCVC());
    }
}
