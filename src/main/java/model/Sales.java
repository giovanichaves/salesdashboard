package model;

import service.SalesAmountBucket;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Sales {

    private ConcurrentHashMap<Integer, SalesAmountBucket> lastMinuteSales = new ConcurrentHashMap<>();
    private float totalSales = 0;
    private int totalOrders = 0;

    public Sales() {
        LocalDateTime now = LocalDateTime.now();
        for (int second = 0; second < 60; second++) {
            lastMinuteSales.put(second, new SalesAmountBucket(now.truncatedTo(MINUTES).plusSeconds(second), this));
        }
    }

    public ConcurrentHashMap<Integer, SalesAmountBucket> getLastMinuteSales() {
        return lastMinuteSales;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public synchronized void addTotalSales(float salesInBucket) {
        totalSales += salesInBucket;
    }

    public synchronized void subTotalSales(float salesInBucket) {
        totalSales -= salesInBucket;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public synchronized void addTotalOrders() {
        totalOrders++;
    }

    public synchronized void subTotalOrders(int ordersInBucket) {
        totalOrders -= ordersInBucket;
    }
}
