package service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SalesAmountBucket {

    private float totalSales = 0;
    private int totalOrders = 0;

    public SalesAmountBucket(LocalDateTime timerStart) {
        TimerTask resetTask = new TimerTask() {
            @Override
            public void run() {
                resetSalesAmount();
            }
        };

        new Timer().scheduleAtFixedRate(
                resetTask,
                Date.from(timerStart.atZone(ZoneId.systemDefault()).toInstant()),
                TimeUnit.SECONDS.toMillis(60)
        );
    }

    public SalesAmountBucket addSalesAmount(float salesAmount) {
        totalSales += salesAmount;
        totalOrders++;

        return this;
    }

    public SalesAmountBucket resetSalesAmount() {
        totalSales = 0;
        totalOrders = 0;

        return this;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public int getTotalOrders() {
        return totalOrders;
    }
}
