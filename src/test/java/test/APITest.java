package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ApiUtils;

public class APITest {
    @Test
    @DisplayName("Отправка валидных данных с разрешенной картой сервису платежей")
    public void payWithApprovedCardAPI() {
        ApiUtils.shouldSendValidPaymentRequestApprovedCard();
    }

    @Test
    @DisplayName("Отправка валидных данных с отклоненной картой сервису платежей")
    public void payWithDeclinedCardAPI() {
        ApiUtils.shouldSendValidPaymentRequestDeclinedCard();
    }

    @Test
    @DisplayName("Отправка валидных данных с разрешенной картой кредитному сервису")
    public void creditWithApprovedCardAPI() {
        ApiUtils.shouldSendValidCreditRequestApprovedCard();
    }

    @Test
    @DisplayName("Отправка валидных данных с отклоненной картой кредитному сервису")
    public void creditWithDeclinedCardAPI() {
        ApiUtils.shouldSendValidCreditRequestDeclinedCard();
    }
}
