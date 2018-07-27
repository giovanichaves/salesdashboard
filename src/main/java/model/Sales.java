package model;

import service.SalesAmountBucket;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Sales {

    private ConcurrentHashMap<Integer, SalesAmountBucket> lastMinuteSales = new ConcurrentHashMap<>();

    public Sales() {
        LocalDateTime now = LocalDateTime.now();
        for (int second = 0; second < 60; second++) {
            lastMinuteSales.put(second, new SalesAmountBucket(now.plusSeconds(second).truncatedTo(SECONDS)));
        }
    }

    public ConcurrentHashMap<Integer, SalesAmountBucket> getLastMinuteSales() {
        return lastMinuteSales;
    }
}
