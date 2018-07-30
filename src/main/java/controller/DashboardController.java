package controller;

import model.SalesStatistics;
import service.SalesService;

import static spark.Spark.get;
import static spark.Spark.post;
import static utils.JsonUtil.asJson;
import static utils.JsonUtil.toJson;

public class DashboardController {

    private final SalesService salesService;

    public DashboardController(SalesService salesService) {
        this.salesService = salesService;

        this.newSaleEndpoint();
        this.statisticsEndpoint();
    }

    private void newSaleEndpoint() {
        post("/sales", (req, res) -> {
            String salesAmountParam = req.queryParams("sales_amount");

            if (salesAmountParam == null) {
                res.status(400);
                res.type("application/json");
                return toJson(new ErrorResponse("sales_amount parameter is required!"));
            }

            Double salesAmount;
            try {
                salesAmount = Double.parseDouble(salesAmountParam);
            } catch(NumberFormatException e) {
                res.status(400);
                res.type("application/json");
                return toJson(new ErrorResponse("sales_amount must be a number"));
            }

            salesService.storeSales(salesAmount);
            res.status(202);
            return "";
        });
    }

    private void statisticsEndpoint() {
        get("/statistics", (req, res) -> {
            SalesStatistics statistics = salesService.getSalesStatistics();

            res.status(200);
            res.type("application/json");
            return new StatisticsResponse(statistics.getTotalSales(), statistics.getOrderAverage());
        }, asJson());
    }



}
