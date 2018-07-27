package controller;

import service.SalesStatistics;
import service.TransactionService;

import static spark.Spark.get;
import static utils.JsonUtil.asJson;
import static utils.JsonUtil.toJson;

public class Dashboard {

    private final TransactionService transactionService;

    public Dashboard(TransactionService transactionService) {
        this.transactionService = transactionService;

        this.transactionsEndpoint();
        this.statisticsEndpoint();
    }

    private void transactionsEndpoint() {
        get("/sales", (req, res) -> {
            String salesAmountParam = req.queryParams("sales_amount");

            if (salesAmountParam == null) {
                res.status(400);
                res.type("application/json");
                return toJson(new ErrorResponse("sales_amount parameter is required!"));
            }

            transactionService.storeSales(Float.parseFloat(salesAmountParam));
            res.status(202);
            return "";
        });
    }

    private void statisticsEndpoint() {
        get("/statistics", (req, res) -> {
            SalesStatistics statistics = transactionService.calculateSalesStatistics();

            res.status(200);
            res.type("application/json");
            return new StatisticsResponse(statistics.getTotalSales(), statistics.getOrderAverage());
        }, asJson());
    }



}
