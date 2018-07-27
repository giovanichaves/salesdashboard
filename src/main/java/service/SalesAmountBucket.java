package service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SalesAmountBucket {

    private final int n;
    private float totalSales = 0;
    private int totalOrders = 0;

    public SalesAmountBucket(int n, LocalDateTime timerStart) {
        this.n = n;
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

        System.out.println("created bucket #"+n+" at "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.S.n")));
    }

    public SalesAmountBucket addSalesAmount(float salesAmount) {
        totalSales += salesAmount;
        totalOrders++;
        System.out.println("added "+salesAmount+" to #"+n+" at "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.S.n")));
        System.out.println("total #"+n+" is "+totalSales);
        return this;
    }

    public SalesAmountBucket resetSalesAmount() {
        totalSales = 0;
        totalOrders = 0;
        System.out.println("reset #"+n+" at "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.S.n")));
        return this;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public int getTotalOrders() {
        return totalOrders;
    }
}
