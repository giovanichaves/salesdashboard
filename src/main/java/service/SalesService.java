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


    public synchronized static void flushSecondBucket(Sales sales, int second) {
        sales.getLastMinuteSales().compute(second, (i, bucket) -> {
            System.out.println("flush #"+i);
            System.out.println("bTotal "+bucket.getSalesSum() + " bQty "+bucket.getOrdersQty());
            System.out.println("lTotal "+sales.getLastSecondSales().getSalesSum() + " lQty "+sales.getLastSecondSales().getOrdersQty());
            sales.subTotalSales(bucket.getSalesSum());
            sales.subTotalOrders(bucket.getOrdersQty());

            bucket.setBucket(sales.getLastSecondSales());

            sales.addTotalSales(sales.getLastSecondSales().getSalesSum());
            sales.addTotalOrders(sales.getLastSecondSales().getOrdersQty());

            sales.getLastSecondSales().resetSalesAmount();
            return bucket;
        });
    }

}
