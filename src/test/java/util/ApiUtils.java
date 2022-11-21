package util;

import data.CardInfo;
import data.DataHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ApiUtils {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/api/v1")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static void shouldSendValidPaymentRequestApprovedCard() {
        CardInfo info = new CardInfo(DataHelper.getApprovedCardNumber(),
                DataHelper.getMonthWithShift(0),
                DataHelper.getYearWithShift(1),
                DataHelper.getValidOwnerName(),
                DataHelper.getValidCVC());
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(info))
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    public static void shouldSendValidPaymentRequestDeclinedCard() {
        CardInfo info = new CardInfo(DataHelper.getDeclinedCardNumber(),
                DataHelper.getMonthWithShift(0),
                DataHelper.getYearWithShift(1),
                DataHelper.getValidOwnerName(),
                DataHelper.getValidCVC());
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(info))
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }

    public static void shouldSendValidCreditRequestApprovedCard() {
        CardInfo info = new CardInfo(DataHelper.getApprovedCardNumber(),
                DataHelper.getMonthWithShift(0),
                DataHelper.getYearWithShift(1),
                DataHelper.getValidOwnerName(),
                DataHelper.getValidCVC());
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(info))
                .when()
                .post("/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    public static void shouldSendValidCreditRequestDeclinedCard() {
        CardInfo info = new CardInfo(DataHelper.getDeclinedCardNumber(),
                DataHelper.getMonthWithShift(0),
                DataHelper.getYearWithShift(1),
                DataHelper.getValidOwnerName(),
                DataHelper.getValidCVC());
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(info))
                .when()
                .post("/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }
}
