package model;

public class SalesAmountBucket {
    private double salesSum = 0;
    private int ordersQty = 0;

    public synchronized void addSalesAmount(double salesAmount) {

        synchronized ("lastSecondBucket") {
            this.salesSum += salesAmount;
            this.ordersQty++;
        }

    }

    public double getSalesSum() {
        return this.salesSum;
    }

    public int getOrdersQty() {
        return this.ordersQty;
    }

    public void setBucket(SalesAmountBucket bucket) {
        this.salesSum = bucket.getSalesSum();
        this.ordersQty = bucket.getOrdersQty();
    }

    public void resetBucket() {
        salesSum = 0;
        ordersQty = 0;
    }
}
