package service;

import model.Sales;
import model.SalesAmountBucket;
import utils.TimeProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class SalesService {

    public static ConcurrentHashMap<Integer, SalesAmountBucket> bucketsProvider() {
        ConcurrentHashMap<Integer, SalesAmountBucket> minuteBuckets = new ConcurrentHashMap<>();
        for (int second = 0; second < 60; second++) {
            minuteBuckets.put(second, new SalesAmountBucket());
        }
        return minuteBuckets;
    }

    public static void startBucketsTimer(Sales sales, TimeProvider timeProvider) {
        TimerTask resetTask = new TimerTask() {
            @Override
            public void run() {
                flushSecondBucket(sales, timeProvider.now().getSecond());
            }
        };

        new Timer().scheduleAtFixedRate(
                resetTask,
                0,
                TimeUnit.SECONDS.toMillis(1)
        );
    }


    public static void flushSecondBucket(Sales sales, int second) {
        sales.getLastMinuteSales().compute(second, (i, secondBucket) -> {
            //remove expired bucket amounts from totals
            subBucketTotals(sales, secondBucket);

            //replace expired bucket with new bucket
            SalesAmountBucket lastSecondSales = sales.getLastSecondSales();
            secondBucket.setBucket(lastSecondSales);

            //insert new bucket amounts to totals
            addBucketTotals(sales, lastSecondSales);

            //reset amounts from new bucket
            lastSecondSales.resetBucket();

            //store new bucket
            return secondBucket;
        });
    }

    public static void addBucketTotals(Sales sales, SalesAmountBucket bucket) {
        sales.setTotalSales(sales.getTotalSales() + bucket.getSalesSum());
        sales.setTotalOrders(sales.getTotalOrders() + bucket.getOrdersQty());
    }

    public static void subBucketTotals(Sales sales, SalesAmountBucket bucket) {
        sales.setTotalSales(sales.getTotalSales() - bucket.getSalesSum());
        sales.setTotalOrders(sales.getTotalOrders() - bucket.getOrdersQty());
    }
}
