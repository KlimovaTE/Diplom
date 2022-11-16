package test;

import org.junit.jupiter.api.*;

import page.FormPage;
import page.HomePage;
import util.DBUtil;

import static com.codeborne.selenide.Selenide.*;

public class CreditPositiveTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("credit_request_entity");
    }

    @Test
    @DisplayName("Кредит по одобренной карте. Все поля заполены валидными данными.")
    public void creditWithApprovedCard() {
        HomePage.creditPage();
        FormPage.setValidApprovedCard();
        FormPage.proceedButton.click();
        FormPage.paySuccess();
        Assertions.assertEquals("APPROVED", DBUtil.getOperationStatus("credit_request_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfCredit());
    }

    @Test
    @DisplayName("Кредит по отклоненной карте. Все поля заполены валидными данными.")
    public void creditWithDeclinedCard() {
        HomePage.creditPage();
        FormPage.setValidDeclinedCard();
        FormPage.proceedButton.click();
        FormPage.payError();
        Assertions.assertEquals("DECLINED", DBUtil.getOperationStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfCredit());
    }
}