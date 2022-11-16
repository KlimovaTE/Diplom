package test;

import data.DataHelper;
import org.junit.jupiter.api.*;

import page.FormPage;
import page.HomePage;
import util.DBUtil;

import static com.codeborne.selenide.Selenide.*;

public class PayCardNegativeTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("payment_entity");
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Месяц\". В поле \"Месяц\" введена одна цифра.")
    public void payWithInvalidMonth1() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setMonth2(DataHelper.getOneDigit());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Месяц\". В поле \"Месяц\" введенно \"00\".")
    public void payWithInvalidMonth2() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setMonth2("00");
        FormPage.proceedButton.click();
        FormPage.errorInvalidCardExpirationDate();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Месяц\". В поле \"Месяц\" введенно число число от 13 до 99.")
    public void payWithInvalidMonth3() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setMonth2(DataHelper.getOneNumberFrom13to99());
        FormPage.proceedButton.click();
        FormPage.errorInvalidCardExpirationDate();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Год\". В поле \"Год\" введена одна цифра.")
    public void payWithInvalidYear1() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setYear2(DataHelper.getOneDigit());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Год\". В поле \"Год\" введенно число, равное \"последние две цифры текущий года - 1\"")
    public void payWithInvalidYear2() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setYear2(DataHelper.getLastYear());
        FormPage.proceedButton.click();
        FormPage.errorExpiredCard();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Год\". В поле \"Год\" введенно число, равное \"последние две цифры текущий года + 6\"")
    public void payWithInvalidYear3() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setYear2(DataHelper.getThisYearPlus6());
        FormPage.proceedButton.click();
        FormPage.errorInvalidCardExpirationDate();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме полей \"Год\" и \"Месяц\". Ввод даты, когда срок действия карты истек в прошлом месяце.")
    public void payWithLastMonth() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setMonth2(DataHelper.getThisMonthMinus1());
        FormPage.setYear2(DataHelper.getThisYear());
        FormPage.proceedButton.click();
        FormPage.errorInvalidCardExpirationDate();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка, состоящая из одного слова")
    public void payWithInvalidOwner1() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwnerName2(DataHelper.getInvalidOwnerOnlyOneName());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка, состоящая из трёх слов")
    public void payWithInvalidOwner2() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwnerName2(DataHelper.getInvalidOwnerThreeName());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка,состоящая из двух слов и содержащая цифры")
    public void payWithInvalidOwner3() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwnerName2(DataHelper.getInvalidOwnerNameWithNumber());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка,состоящая из двух слов и содержащая буквы кириллицы")
    public void payWithInvalidOwner4() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwnerName2(DataHelper.getInvalidOwnerNameWithCyrillic());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка,состоящая из двух слов и содержащая спецсимволы")
    public void payWithInvalidOwner5() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwnerName2(DataHelper.getInvalidOwnerOnlyOneName() + " " + " \"[|]'~<!--@/*$%^&#*/()?>,.*/\\");
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка,состоящая из двух слов. Общая длина строки 65 символов")
    public void payWithInvalidOwner6() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwnerName2(DataHelper.getInvalidOwnerNameLength65());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка <script>alert(\"I hacked this!\")</script>")
    public void payWithInvalidOwner7() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwnerName2("<script>alert(\"I hacked this!\")</script>");
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"Владелец\". В поле \"Владелец\" введена строка FOO’); DROP TABLE USERS;")
    public void payWithInvalidOwner8() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwnerName2("FOO’); DROP TABLE USERS;");
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"CVC/CVV\". В поле \"CVC/CVV\" введена одна цифра.")
    public void payWithInvalidCVC1() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setCVC2(DataHelper.getOneDigit());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    @DisplayName("Платеж. Заполнение всех полей валидными данными, кроме поля \"CVC/CVV\". В поле \"CVC/CVV\" введены две цифры.")
    public void payWithInvalidCVC2() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setCVC2(DataHelper.getOneDigit() + DataHelper.getOneDigit());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }
}