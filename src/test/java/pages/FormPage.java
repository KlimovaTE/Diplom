package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Data;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data

public class FormPage {
    private final SelenideElement cardField = $$("input").get(0);
    public final SelenideElement monthField = $$("input").get(1);
    public final SelenideElement yearField = $$("input").get(2);
    public final SelenideElement ownerField = $$("input").get(3);
    public final SelenideElement cvcField = $$("input").get(4);
    public final SelenideElement proceedButton = $(".form-field .button");
    private final SelenideElement textSuccess = $(".notification_status_ok");
    private final SelenideElement textError = $(".notification_status_error");
    private final SelenideElement textErrorInvalidFormat = $(".input__sub");
    private final List<SelenideElement> textErrorInvalidFormatList = $$(".input__sub");

    public void paySuccess() {
        textSuccess.shouldHave(text("Операция одобрена Банком."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void payError() {
        textError.shouldHave(text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void errorInvalidFormat() {
        textErrorInvalidFormat.shouldHave(text("Неверный формат"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public void errorInvalidCardExpirationDate() {
        textErrorInvalidFormat.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public void errorExpiredCard() {
        textErrorInvalidFormat.shouldHave(text("Истёк срок действия карты"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public void errorRequiredField() {
        for (SelenideElement element : textErrorInvalidFormatList) {
            element.shouldHave(text("Поле обязательно для заполнения"), Duration.ofSeconds(2)).shouldBe(visible);
        }
    }

    public void setCard(String cardNumber) {
        cardField.setValue(cardNumber);
    }

    public void setMonth(String month) {
        monthField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        monthField.setValue(month);
    }

    public void setYear(String year) {
        yearField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        yearField.setValue(year);
    }

    public void setOwnerName(String ownerName) {
        ownerField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        ownerField.setValue(ownerName);
    }

    public void setCVC(String cvc) {
        cvcField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        cvcField.setValue(cvc);
    }

    public void setValidCard(String cardNumber) {
        this.setCard(cardNumber);
        this.setMonth(DataHelper.getMonthWithShift(0));
        this.setYear(DataHelper.getYearWithShift(1));
        this.setOwnerName(DataHelper.getValidOwnerName());
        this.setCVC(DataHelper.getValidCVC());
    }
}
