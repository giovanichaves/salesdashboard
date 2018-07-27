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

    public SalesStatistics calculateSalesStatistics() {
        float totalSales = 0;
        int totalOrders = 0;

        for (SalesAmountBucket bucket : sales.getLastMinuteSales().values()) {
            totalSales += bucket.getTotalSales();
            totalOrders += bucket.getTotalOrders();
        }

        return new SalesStatistics(totalSales, totalOrders);
    }
}
