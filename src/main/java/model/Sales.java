package model;

import java.util.concurrent.ConcurrentHashMap;

public class Sales {

    private final ConcurrentHashMap<Integer, SalesAmountBucket> lastMinuteBuckets;
    private final SalesAmountBucket lastSecondBucket;
    private double totalSales = 0;
    private int totalOrders = 0;

    public Sales(ConcurrentHashMap<Integer, SalesAmountBucket> minuteBuckets, SalesAmountBucket lastSecondBucket) {
        this.lastMinuteBuckets = minuteBuckets;
        this.lastSecondBucket = lastSecondBucket;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public ConcurrentHashMap<Integer, SalesAmountBucket> getLastMinuteBuckets() {
        return lastMinuteBuckets;
    }

    public SalesAmountBucket getLastSecondBucket() {
        return lastSecondBucket;
    }
}
