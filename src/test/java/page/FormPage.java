package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Data;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data

public class FormPage {
    private SelenideElement cardField = $$("input").get(0);
    public SelenideElement monthField = $$("input").get(1);
    public SelenideElement yearField = $$("input").get(2);
    public SelenideElement ownerField = $$("input").get(3);
    public SelenideElement cvcField = $$("input").get(4);
    public SelenideElement proceedButton = $(".form-field .button");
    private SelenideElement textSuccess = $(".notification__content");

    public SelenideElement success() {
        return textSuccess.shouldHave(text("Операция одобрена Банком."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void getApprovedCard() {
        cardField.setValue(DataHelper.getApprovedCardNumber());
    }

    public void getValidMonth() {
        monthField.setValue(DataHelper.getValidMonthRandom());
    }

    public void getNextYear() {
        yearField.setValue(DataHelper.getNextYear());
    }

    public void getValidOwnerName() {
        ownerField.setValue(DataHelper.getValidOwnerName());
    }
    public void getValidCVC() {
        cvcField.setValue(DataHelper.getValidCVC());
    }


    //    public SelenideElement success()
//    {
//        return textError.shouldHave(text("Недостаточный баланс для перевода"));
//    }
}
