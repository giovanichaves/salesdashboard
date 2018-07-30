package service;

import model.Sales;
import model.SalesAmountBucket;
import model.SalesStatistics;
import utils.TimeProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class SalesService {

    private final Sales sales;

    public SalesService(Sales sales) {
        this.sales = sales;
    }

    public void storeSales(double salesAmount) {
        sales.getLastSecondBucket().addSalesAmount(salesAmount);
    }

    public SalesStatistics getSalesStatistics() {
        return new SalesStatistics(sales.getTotalSales(), sales.getTotalOrders());
    }

    public static ConcurrentHashMap<Integer, SalesAmountBucket> bucketsProvider() {
        ConcurrentHashMap<Integer, SalesAmountBucket> minuteBuckets = new ConcurrentHashMap<>();
        for (int second = 0; second < 60; second++) {
            minuteBuckets.put(second, new SalesAmountBucket());
        }
        return minuteBuckets;
    }

    public void startBucketsTimer(TimeProvider timeProvider) {
        TimerTask resetTask = new TimerTask() {
            @Override
            public void run() {
                flushLastSecondBucket(timeProvider.now().getSecond());
            }
        };

        new Timer().scheduleAtFixedRate(
                resetTask,
                0,
                TimeUnit.SECONDS.toMillis(1)
        );
    }


    public void flushLastSecondBucket(int second) {
        sales.getLastMinuteBuckets().compute(second, (i, secondBucket) -> {
            //remove expired bucket amounts from totals
            subBucketTotals(secondBucket);

            //replace expired bucket with new bucket
            SalesAmountBucket lastSecondBucket = sales.getLastSecondBucket();
            secondBucket.setBucket(lastSecondBucket);

            //insert new bucket amounts to totals
            addBucketTotals(lastSecondBucket);

            //reset amounts from new bucket
            lastSecondBucket.resetBucket();

            //store new bucket
            return secondBucket;
        });
    }

    public void addBucketTotals(SalesAmountBucket bucket) {
        sales.setTotalSales(sales.getTotalSales() + bucket.getSalesSum());
        sales.setTotalOrders(sales.getTotalOrders() + bucket.getOrdersQty());
    }

    public void subBucketTotals(SalesAmountBucket bucket) {
        sales.setTotalSales(sales.getTotalSales() - bucket.getSalesSum());
        sales.setTotalOrders(sales.getTotalOrders() - bucket.getOrdersQty());
    }
}
