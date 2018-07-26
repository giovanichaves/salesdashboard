package service;

public class SalesAmountBucket {

    private float totalSales = 0;
    private int totalOrders = 0;

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
