
package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private final SelenideElement paymentButton = $(".button");
    private final SelenideElement creditButton = $(".button_view_extra");

    public FormPage paymentPage() {
        this.paymentButton.click();
        return new FormPage();
    }

    public FormPage creditPage() {
        this.creditButton.click();
        return new FormPage();
    }
}
