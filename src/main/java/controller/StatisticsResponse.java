package controller;

public class StatisticsResponse {

    private double total_sales_amount;
    private double average_amount_per_order;
    private final int qty;

    public StatisticsResponse(double total_sales_amount, double average_amount_per_order, int qty) {
        this.total_sales_amount = total_sales_amount;
        this.average_amount_per_order = average_amount_per_order;
        this.qty = qty;
    }
}
