package test.api;

import data.DataHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ApiUtils;

public class APITest {
    @Test
    @DisplayName("Отправка валидных данных с разрешенной картой сервису платежей")
    public void payWithApprovedCardAPI() {
        ApiUtils.shouldSendPaymentRequest(DataHelper.getApprovedCardNumber(), "APPROVED");
    }

    @Test
    @DisplayName("Отправка валидных данных с отклоненной картой сервису платежей")
    public void payWithDeclinedCardAPI() {
        ApiUtils.shouldSendPaymentRequest(DataHelper.getDeclinedCardNumber(), "DECLINED");
    }

    @Test
    @DisplayName("Отправка валидных данных с разрешенной картой кредитному сервису")
    public void creditWithApprovedCardAPI() {
        ApiUtils.shouldSendCreditRequest(DataHelper.getApprovedCardNumber(), "APPROVED");
    }

    @Test
    @DisplayName("Отправка валидных данных с отклоненной картой кредитному сервису")
    public void creditWithDeclinedCardAPI() {
        ApiUtils.shouldSendCreditRequest(DataHelper.getDeclinedCardNumber(), "DECLINED");
    }
}
