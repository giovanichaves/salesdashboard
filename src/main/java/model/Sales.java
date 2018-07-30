package model;

import java.util.concurrent.ConcurrentHashMap;

public class Sales {

    private ConcurrentHashMap<Integer, SalesAmountBucket> lastMinuteSales;
    private volatile SalesAmountBucket lastSecondSales;
    private double totalSales = 0;
    private int totalOrders = 0;

    public Sales(ConcurrentHashMap<Integer, SalesAmountBucket> minuteBuckets, SalesAmountBucket lastSecondSales) {
        this.lastMinuteSales = minuteBuckets;
        this.lastSecondSales = lastSecondSales;
    }

    public void addSecondSales(double salesAmount) {
        lastSecondSales.addSalesAmount(salesAmount);
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void addTotalSales(double salesInBucket) {
        totalSales += salesInBucket;
    }

    public void subTotalSales(double salesInBucket) {
        totalSales -= salesInBucket;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void addTotalOrders(int ordersInBucket) {
        totalOrders += ordersInBucket;
    }

    public void subTotalOrders(int ordersInBucket) {
        totalOrders -= ordersInBucket;
    }

    public ConcurrentHashMap<Integer, SalesAmountBucket> getLastMinuteSales() {
        return lastMinuteSales;
    }

    public SalesAmountBucket getLastSecondSales() {
        return lastSecondSales;
    }
}
