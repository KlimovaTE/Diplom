package test;

import org.junit.jupiter.api.*;

import page.HomePage;
import util.ApiUtils;
import util.DBUtil;

import static com.codeborne.selenide.Selenide.*;

public class PositiveTestPayCard {
    @BeforeEach
    public void setOpen() {
        open("http://localhost:8080");
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("payment_entity");
    }

    @Test
    public void payWithApprovedCard() {
        var formPage = HomePage.paymentPage();
        formPage.getApprovedCard();
        formPage.getValidMonth();
        formPage.getNextYear();
        formPage.getValidOwnerName();
        formPage.getValidCVC();
        formPage.proceedButton.click();
        formPage.success();
        Assertions.assertEquals("APPROVED", DBUtil.getPaymentStatus());
        Assertions.assertEquals("1", DBUtil.countOrdersIfPayment());
        ApiUtils.shouldSendValidPaymentRequest();
    }
}
