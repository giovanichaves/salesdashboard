package service;

import model.Sales;
import model.SalesAmountBucket;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SalesServiceTest {

    @Test
    public void testProvides60Buckets() {
        Map<Integer, SalesAmountBucket> minuteBuckets = SalesService.bucketsProvider();
        assertThat(minuteBuckets.size(), equalTo((int)TimeUnit.MINUTES.toSeconds(1)));
    }

    @Test
    public void testStartsBucketsTimer() {

    }

    @Test
    public void testFlushesSecondBucket() {
        ConcurrentHashMap<Integer, SalesAmountBucket> buckets = new ConcurrentHashMap<>();
        buckets.put(5, new SalesAmountBucket());
        buckets.put(10, new SalesAmountBucket());

        Sales sales = new Sales(buckets, new SalesAmountBucket());
        SalesService salesService = new SalesService(sales);

        salesService.storeSales(20);
        salesService.storeSales(20);
        salesService.storeSales(20);

        salesService.flushLastSecondBucket(5);

        salesService.storeSales(20);

        salesService.flushLastSecondBucket(10);

        assertThat(buckets.get(5).getSalesSum(), equalTo(60.0));
        assertThat(buckets.get(5).getOrdersQty(), equalTo(3));

        assertThat(sales.getTotalSales(), equalTo(80.0));
        assertThat(sales.getTotalOrders(), equalTo(4));
    }

    @Test
    public void testAddsBucketTotals() {
        Sales sales = new Sales(SalesService.bucketsProvider(), new SalesAmountBucket());
        SalesService salesService = new SalesService(sales);
        SalesAmountBucket otherBucket = new SalesAmountBucket();

        sales.setTotalSales(15);
        sales.setTotalOrders(2);

        otherBucket.addSalesAmount(10.5);

        salesService.addBucketTotals(otherBucket);

        assertThat(sales.getTotalSales(), equalTo(25.5));
        assertThat(sales.getTotalOrders(), equalTo(3));
    }

    @Test
    public void subBucket() {
        Sales sales = new Sales(SalesService.bucketsProvider(), new SalesAmountBucket());
        SalesService salesService = new SalesService(sales);
        SalesAmountBucket otherBucket = new SalesAmountBucket();

        sales.setTotalSales(15);
        sales.setTotalOrders(2);

        otherBucket.addSalesAmount(10.5);

        salesService.subBucketTotals(otherBucket);

        assertThat(sales.getTotalSales(), equalTo(4.5));
        assertThat(sales.getTotalOrders(), equalTo(1));
    }

}