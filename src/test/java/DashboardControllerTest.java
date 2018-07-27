import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

class DashboardControllerTest {

    @BeforeClass
    public static void beforeClass() {
        DashboardApplication.main(null);
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void shouldReturnErrorWhenSalesParameterMissing() throws IOException {
        HttpResponse res = Request.Post("http://localhost:4567/sales").execute().returnResponse();

        assertEquals(res.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST);
//        assertEquals(res.getEntity()., "sales_amount parameter is required!");
    }

}