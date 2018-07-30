import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.isEmptyString;

public class DashboardControllerTest {

    @BeforeClass
    public static void beforeClass() {
        DashboardApplication.main(null);
        RestAssured.baseURI = "http://localhost:4567";
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void shouldReturnErrorWhenSalesParameterMissing() {
        post("/sales").then()
            .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("errorMessage", equalTo("sales_amount parameter is required!"));
    }

    @Test
    public void shouldReturnErrorWhenSalesAmountIsNaN() {
        given()
                .queryParam("sales_amount", "not-a-number")
        .post("/sales").then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("errorMessage", equalTo("sales_amount must be a number"));
    }

    @Test
    public void shouldStoreTransactionAndStatusAcceptedWithEmptyBody() {
        given()
                .queryParam("sales_amount", "10.00")
        .post("/sales").then()
            .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .body(isEmptyString());
    }

    @Test
    public void shouldReturnTotalSalesAndAverage() {
        get("/statistics").then()
            .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("total_sales_amount", not(empty()))
                .body("average_amount_per_order", not(empty()));
    }
}