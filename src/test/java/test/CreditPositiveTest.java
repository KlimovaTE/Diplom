package test;

import data.DataHelper;
import org.junit.jupiter.api.*;

import pages.FormPage;
import pages.HomePage;
import util.DBUtil;

import static com.codeborne.selenide.Selenide.*;

public class CreditPositiveTest {
    HomePage homePage = new HomePage();

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("credit_request_entity");
    }

    @Test
    @DisplayName("Кредит по одобренной карте. Все поля заполены валидными данными.")
    public void creditWithApprovedCard() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.proceedButton.click();
        formPage.paySuccess();
        Assertions.assertEquals("APPROVED", DBUtil.getOperationStatus("credit_request_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfCredit());
    }

    @Test
    @DisplayName("Кредит по отклоненной карте. Все поля заполены валидными данными.")
    public void creditWithDeclinedCard() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getDeclinedCardNumber());
        formPage.proceedButton.click();
        formPage.payError();
        Assertions.assertEquals("DECLINED", DBUtil.getOperationStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfCredit());
    }
}