package model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SalesStatisticsTest {

    @Test
    public void testAverageFromZeroSales() {
        SalesStatistics statistics = new SalesStatistics(0, 0);
        assertThat(statistics.getOrderAverage(), equalTo(0.0));
    }

    @Test
    public void testAverageCalculation() {
        SalesStatistics statistics = new SalesStatistics(20.50, 2);
        assertThat(statistics.getOrderAverage(), equalTo(10.25));
    }
}