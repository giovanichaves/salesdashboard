package service;

public class SalesStatistics {

    private float totalSales = 0;
    private float totalOrders = 0;

    public SalesStatistics(float totalSales, float totalOrders) {
        this.totalSales = totalSales;
        this.totalOrders = totalOrders;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public float getOrderAverage() {
        return totalOrders == 0 ? 0 : totalSales / totalOrders;
    }
}
