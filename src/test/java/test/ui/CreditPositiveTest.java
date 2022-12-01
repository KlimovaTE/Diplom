package test.ui;

import data.DataHelper;
import data.URL;
import org.junit.jupiter.api.*;

import pages.FormPage;
import pages.HomePage;
import util.DBUtil;

import static com.codeborne.selenide.Selenide.*;

public class CreditPositiveTest {
    HomePage homePage = new HomePage();

    @BeforeEach
    public void setUp() {
        open(URL.getURL());
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("credit_request_entity");
    }

    @Test
    @DisplayName("Кредит по одобренной карте. Все поля заполены валидными данными.")
    public void creditWithApprovedCard() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.proceedButton.click();
        formPage.checkPaymentIsSuccessful();
        Assertions.assertEquals("APPROVED", DBUtil.getOperationStatus("credit_request_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfCredit());
    }

    @Test
    @DisplayName("Кредит по отклоненной карте. Все поля заполены валидными данными.")
    public void creditWithDeclinedCard() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getDeclinedCardNumber());
        formPage.proceedButton.click();
        formPage.checkPaymentIsRejected();
        Assertions.assertEquals("DECLINED", DBUtil.getOperationStatus("credit_request_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrdersIfCredit());
    }
}