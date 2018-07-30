package model;

import java.util.concurrent.ConcurrentHashMap;

public class Sales {

    private ConcurrentHashMap<Integer, SalesAmountBucket> lastMinuteSales;
    private SalesAmountBucket lastSecondSales;
    private double totalSales = 0;
    private int totalOrders = 0;

    public Sales(ConcurrentHashMap<Integer, SalesAmountBucket> minuteBuckets, SalesAmountBucket lastSecondSales) {
        this.lastMinuteSales = minuteBuckets;
        this.lastSecondSales = lastSecondSales;
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

    public ConcurrentHashMap<Integer, SalesAmountBucket> getLastMinuteSales() {
        return lastMinuteSales;
    }

    public SalesAmountBucket getLastSecondSales() {
        return lastSecondSales;
    }
}
