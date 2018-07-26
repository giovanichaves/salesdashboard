package service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import static java.time.temporal.ChronoUnit.SECONDS;

public class TransactionService {

    private ConcurrentHashMap<Integer, SalesAmountBucket> lastMinuteSales = new ConcurrentHashMap<>();
    private LocalDateTime lastTime = LocalDateTime.now();

    public TransactionService() {
        for (int second = 0; second < 60; second++) {
            lastMinuteSales.put(second, new SalesAmountBucket());
        }
    }

    public void storeSales(float salesAmount) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.truncatedTo(SECONDS) != lastTime.truncatedTo(SECONDS)) {

            LocalDateTime lastTimeAux = lastTime;
            lastTime = currentTime;

            clearExpiredBuckets(currentTime, lastTimeAux);
        }

        updateBucket(currentTime, salesAmount);
    }

    public void clearExpiredBuckets(LocalDateTime fromTime, LocalDateTime toTime) {
        int minuteLimit = 59;

        while (fromTime.isAfter(toTime) && minuteLimit-- > 0) {
            lastMinuteSales.compute(fromTime.getSecond(), (second, bucket) -> bucket.resetSalesAmount());
            fromTime = fromTime.minusSeconds(1);
        }
    }

    private void updateBucket(LocalDateTime currentTime, float salesAmount) {
        lastMinuteSales.compute(currentTime.getSecond(), (second, bucket) -> bucket.addSalesAmount(salesAmount));
    }

    public SalesStatistics calculateSalesStatistics() {
        float totalSales = 0;
        int totalOrders = 0;

        for (SalesAmountBucket bucket : lastMinuteSales.values()) {
            totalSales += bucket.getTotalSales();
            totalOrders += bucket.getTotalOrders();
        }

        return new SalesStatistics(totalSales, totalOrders);
    }
}
