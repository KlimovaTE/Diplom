package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Data;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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

    public void checkPaymentIsSuccessful() {
        textSuccess.shouldHave(text("Операция одобрена Банком."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void checkPaymentIsRejected() {
        textError.shouldHave(text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void assertErrorInvalidFormat() {
        textErrorInvalidFormat.shouldHave(text("Неверный формат")).shouldBe(visible);
    }

    public void assertErrorInvalidCardExpirationDate() {
        textErrorInvalidFormat.shouldHave(text("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void assertErrorExpiredCard() {
        textErrorInvalidFormat.shouldHave(text("Истёк срок действия карты")).shouldBe(visible);
    }

    public void assertErrorRequiredField() {
        for (SelenideElement element : textErrorInvalidFormatList) {
            element.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
        }
    }

    private void cleanField(SelenideElement fieldName) {
        fieldName.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
    }

    public void setCard(String cardNumber) {
        cardField.setValue(cardNumber);
    }

    public void setMonth(String month) {
        cleanField(monthField);
        monthField.setValue(month);
    }

    public void setYear(String year) {
        cleanField(yearField);
        yearField.setValue(year);
    }

    public void setOwnerName(String ownerName) {
        cleanField(ownerField);
        ownerField.setValue(ownerName);
    }

    public void setCVC(String cvc) {
        cleanField(cvcField);
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
