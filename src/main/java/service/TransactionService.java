package service;

import model.Sales;
import model.SalesStatistics;
import utils.TimeProvider;

public class TransactionService {

    private final Sales sales;

    public TransactionService(Sales sales, TimeProvider timeProvider) {
        this.sales = sales;
        SalesService.startBucketsTimer(sales, timeProvider);
    }

    public void storeSales(double salesAmount) {
        sales.addSecondSales(salesAmount);
    }

    public SalesStatistics getSalesStatistics() {
        return new SalesStatistics(sales.getTotalSales(), sales.getTotalOrders());
    }

}
