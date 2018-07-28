package service;

public class SalesStatistics {

    private float totalSales = 0;
    private int totalOrders = 0;

    public SalesStatistics(float totalSales, int totalOrders) {
        this.totalSales = totalSales;
        this.totalOrders = totalOrders;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public float getOrderAverage() {
        return totalOrders == 0 ? 0 : totalSales / totalOrders;
    }

    public int getTotalOrders() {
        return totalOrders;
    }
}
