package model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SalesAmountBucketTest {
    private SalesAmountBucket salesAmountBucket = new SalesAmountBucket();

    @Test
    public void testResetsTotals() {
        salesAmountBucket.addSalesAmount(10);
        salesAmountBucket.addSalesAmount(10);

        salesAmountBucket.resetBucket();

        assertThat(salesAmountBucket.getSalesSum(), equalTo(0.0));
        assertThat(salesAmountBucket.getOrdersQty(), equalTo(0));
    }

    @Test
    public void testAddsSalesAmountAndIncrementsOrders() {
        salesAmountBucket.addSalesAmount(10);
        salesAmountBucket.addSalesAmount(10);

        assertThat(salesAmountBucket.getSalesSum(), equalTo(20.0));
        assertThat(salesAmountBucket.getOrdersQty(), equalTo(2));
    }

    @Test
    public void testSetsTotals() {
        SalesAmountBucket otherBucket = new SalesAmountBucket();
        otherBucket.addSalesAmount(10);
        otherBucket.addSalesAmount(15);
        otherBucket.addSalesAmount(20);
        salesAmountBucket.setBucket(otherBucket);

        assertThat(salesAmountBucket.getSalesSum(), equalTo(45.0));
        assertThat(salesAmountBucket.getOrdersQty(), equalTo(3));
    }

}