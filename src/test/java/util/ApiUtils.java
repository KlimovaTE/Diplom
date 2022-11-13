package util;

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
//            .setBasePath("/api/v1/pay")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void shouldSendValidPaymentRequest() {
        String requestBody = "{\n" +
                "  \"number\": \"" + DataHelper.getApprovedCardNumber() + "\",\n" +
                "  \"year\": \"" + DataHelper.getNextYear() + "\",\n" +
                "  \"month\": \"" + DataHelper.getValidMonthRandom() + "\",\n" +
                "  \"holder\": \"" + DataHelper.getValidOwnerName() + "\",\n" +
                "  \"cvc\": \"" + DataHelper.getValidCVC() + "\"\n" +
                "}";
        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/payment")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }
}
