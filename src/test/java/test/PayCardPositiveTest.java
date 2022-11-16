package test;

import org.junit.jupiter.api.*;

import page.FormPage;
import page.HomePage;
import util.DBUtil;

import static com.codeborne.selenide.Selenide.*;

public class PayCardPositiveTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("payment_entity");
    }

    @Test
    @DisplayName("Платеж по одобренной карте. Все поля заполены валидными данными.")
    public void payWithApprovedCard() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.proceedButton.click();
        FormPage.paySuccess();
        Assertions.assertEquals("APPROVED", DBUtil.getOperationStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfPayment());
    }

    @Test
    @DisplayName("Платеж по отклоненной карте. Все поля заполены валидными данными.")
    public void payWithDeclinedCard() {
        HomePage.paymentPage();
        FormPage.setValidDeclinedCard();
        FormPage.proceedButton.click();
        FormPage.payError();
        Assertions.assertEquals("DECLINED", DBUtil.getOperationStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfPayment());
    }
}
