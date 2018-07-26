package controller;

public class StatisticsResponse {

    private float total_sales_amount;
    private float average_amount_per_order;

    public StatisticsResponse(float total_sales_amount, float average_amount_per_order) {
        this.total_sales_amount = total_sales_amount;
        this.average_amount_per_order = average_amount_per_order;
    }
}
