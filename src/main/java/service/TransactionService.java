package service;

import model.Sales;

public class TransactionService {

    private final Sales sales;
    private final TimeProvider timeProvider;

    public TransactionService(Sales sales, TimeProvider timeProvider) {
        this.sales = sales;
        this.timeProvider = timeProvider;
    }

    public void storeSales(float salesAmount) {
        sales.getLastMinuteSales()
                .compute(timeProvider.now().getSecond(), (second, bucket) -> bucket.addSalesAmount(salesAmount));
    }

    public SalesStatistics getSalesStatistics() {
        return new SalesStatistics(sales.getTotalSales(), sales.getTotalOrders());
    }
}
