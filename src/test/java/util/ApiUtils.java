package util;

import data.CardInfo;
import data.DataHelper;
import data.URL;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiUtils {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(URL.getURL())
            .setBasePath("/api/v1")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static CardInfo getCardInfo(String cardNumber) {
        return new CardInfo(cardNumber,
                DataHelper.getMonthWithShift(0),
                DataHelper.getYearWithShift(1),
                DataHelper.getValidOwnerName(),
                DataHelper.getValidCVC());
    }

    public static void shouldSendPaymentRequest(String cardNumber, String cardStatus) {
        CardInfo info = getCardInfo(cardNumber);
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(info))
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .body("status", equalTo(cardStatus));
    }

    public static void shouldSendCreditRequest(String cardNumber, String cardStatus) {
        CardInfo info = getCardInfo(cardNumber);
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(info))
                .when()
                .post("/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo(cardStatus));
    }
}
