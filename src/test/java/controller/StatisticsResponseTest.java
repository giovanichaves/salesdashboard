package controller;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatisticsResponseTest {

    @Test
    public void testNumbersFormat() {
        StatisticsResponse response = new StatisticsResponse(10, 5.25);

        assertThat(response.getTotal_sales_amount(), equalTo("10.00"));
        assertThat(response.getAverage_amount_per_order(), equalTo("5.25"));
    }

}