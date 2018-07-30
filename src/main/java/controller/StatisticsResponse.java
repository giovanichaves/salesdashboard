package controller;

public class StatisticsResponse {

    private String total_sales_amount;
    private String average_amount_per_order;

    public StatisticsResponse(double total_sales_amount, double average_amount_per_order) {
        this.total_sales_amount = String.format("%.2f", total_sales_amount);
        this.average_amount_per_order = String.format("%.2f", average_amount_per_order);
    }
}
