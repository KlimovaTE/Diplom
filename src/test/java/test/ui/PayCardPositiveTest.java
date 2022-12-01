package test.ui;

import data.DataHelper;
import org.junit.jupiter.api.*;

import pages.FormPage;
import pages.HomePage;
import util.DBUtil;

public class PayCardPositiveTest extends BaseTest {
    HomePage homePage = new HomePage();

    @Test
    @DisplayName("Платеж по одобренной карте. Все поля заполены валидными данными.")
    public void payWithApprovedCard() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.proceedButton.click();
        formPage.checkPaymentIsSuccessful();
        Assertions.assertEquals("APPROVED", DBUtil.getOperationStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfPayment());
    }

    @Test
    @DisplayName("Платеж по отклоненной карте. Все поля заполены валидными данными.")
    public void payWithDeclinedCard() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getDeclinedCardNumber());
        formPage.proceedButton.click();
        formPage.checkPaymentIsRejected();
        Assertions.assertEquals("DECLINED", DBUtil.getOperationStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfPayment());
    }
}
