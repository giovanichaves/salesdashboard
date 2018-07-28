package service;

import model.Sales;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SalesAmountBucket {
    private final Sales sales;
    private float salesSum = 0;
    private int ordersQty = 0;

    public SalesAmountBucket(LocalDateTime timerStart, Sales sales) {
        this.sales = sales;
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

    SalesAmountBucket addSalesAmount(float salesAmount) {
        sales.addTotalSales(salesAmount);
        sales.addTotalOrders();

        salesSum += salesAmount;
        ordersQty++;

        return this;
    }

    private void resetSalesAmount() {
        sales.subTotalSales(salesSum);
        sales.subTotalOrders(ordersQty);

        salesSum = 0;
        ordersQty = 0;
    }

}
