package model;

public class SalesStatistics {

    private double totalSales = 0;
    private int totalOrders = 0;

    public SalesStatistics(double totalSales, int totalOrders) {
        this.totalSales = totalSales;
        this.totalOrders = totalOrders;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getOrderAverage() {
        return totalOrders == 0 ? 0 : totalSales / totalOrders;
    }

    public int getQty() { return totalOrders; }

}
