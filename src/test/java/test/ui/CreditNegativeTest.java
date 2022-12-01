package test.ui;

import data.DataHelper;
import data.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FormPage;
import pages.HomePage;
import util.DBUtil;

import static com.codeborne.selenide.Selenide.open;

public class CreditNegativeTest {
    HomePage homePage = new HomePage();

    @BeforeEach
    public void setUp() {
        open(URL.getURL());
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("credit_request_entity");
    }

    @Test
    @DisplayName("Кредит. Отправка незаполненной формы.")
    public void payWithBlankForm() {
        FormPage formPage = homePage.creditPage();
        formPage.proceedButton.click();
        formPage.assertErrorRequiredField();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Месяц\". В поле \"Месяц\" введена одна цифра.")
    public void payWithInvalidMonth1() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setMonth(DataHelper.getNumeric(1));
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Месяц\". В поле \"Месяц\" введенно \"00\".")
    public void payWithInvalidMonth2() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setMonth("00");
        formPage.proceedButton.click();
        formPage.assertErrorInvalidCardExpirationDate();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Месяц\". В поле \"Месяц\" введенно число число от 13 до 99.")
    public void payWithInvalidMonth3() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setMonth(DataHelper.getOneNumberFrom13to99());
        formPage.proceedButton.click();
        formPage.assertErrorInvalidCardExpirationDate();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Год\". В поле \"Год\" введена одна цифра.")
    public void payWithInvalidYear1() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setYear(DataHelper.getNumeric(1));
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Год\". В поле \"Год\" введенно число, равное \"последние две цифры текущий года - 1\"")
    public void payWithInvalidYear2() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setYear(DataHelper.getYearWithShift(-1));
        formPage.proceedButton.click();
        formPage.assertErrorExpiredCard();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Год\". В поле \"Год\" введенно число, равное \"последние две цифры текущий года + 6\"")
    public void payWithInvalidYear3() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setYear(DataHelper.getYearWithShift(6));
        formPage.proceedButton.click();
        formPage.assertErrorInvalidCardExpirationDate();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме полей \"Год\" и \"Месяц\". Ввод даты, когда срок действия карты истек в прошлом месяце.")
    public void payWithLastMonth() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setMonth(DataHelper.getMonthWithShift(-1));
        formPage.setYear(DataHelper.getYearWithShift(0));
        formPage.proceedButton.click();
        formPage.assertErrorInvalidCardExpirationDate();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка, состоящая из одного слова")
    public void payWithInvalidOwner1() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwnerName(DataHelper.getInvalidOwnerOnlyOneName());
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка, состоящая из трёх слов")
    public void payWithInvalidOwner2() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwnerName(DataHelper.getInvalidOwnerThreeName());
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка,состоящая из двух слов и содержащая цифры")
    public void payWithInvalidOwner3() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwnerName(DataHelper.getInvalidOwnerNameWithNumber());
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка,состоящая из двух слов и содержащая буквы кириллицы")
    public void payWithInvalidOwner4() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwnerName(DataHelper.getInvalidOwnerNameWithCyrillic());
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка,состоящая из двух слов и содержащая спецсимволы")
    public void payWithInvalidOwner5() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwnerName(DataHelper.getInvalidOwnerOnlyOneName() + " " + " \"[|]'~<!--@/*$%^&#*/()?>,.*/\\");
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка,состоящая из двух слов. Общая длина строки 65 символов")
    public void payWithInvalidOwner6() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwnerName(DataHelper.getInvalidOwnerNameLength65());
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка <script>alert(\"I hacked this!\")</script>")
    public void payWithInvalidOwner7() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwnerName("<script>alert(\"I hacked this!\")</script>");
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка FOO’); DROP TABLE USERS;")
    public void payWithInvalidOwner8() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwnerName("FOO’); DROP TABLE USERS;");
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"CVC/CVV\". В поле \"CVC/CVV\" введена одна цифра.")
    public void payWithInvalidCVC1() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setCVC(DataHelper.getNumeric(1));
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }

    @Test
    @DisplayName("Кредит. Заполнение всех полей валидными данными, кроме поля \"CVC/CVV\". В поле \"CVC/CVV\" введены две цифры.")
    public void payWithInvalidCVC2() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setCVC(DataHelper.getNumeric(2));
        formPage.proceedButton.click();
        formPage.assertErrorInvalidFormat();
    }
}
