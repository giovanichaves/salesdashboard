package controller;

public class StatisticsResponse {

    private double total_sales_amount;
    private double average_amount_per_order;

    public StatisticsResponse(double total_sales_amount, double average_amount_per_order) {
        this.total_sales_amount = total_sales_amount;
        this.average_amount_per_order = average_amount_per_order;
    }

    public double getTotal_sales_amount() {
        return total_sales_amount;
    }

    public double getAverage_amount_per_order() {
        return average_amount_per_order;
    }
}
