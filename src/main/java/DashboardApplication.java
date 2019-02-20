import controller.DashboardController;
import model.Sales;
import model.SalesAmountBucket;
import service.MinuteBucketsProvider;
import service.SalesService;
import utils.TimeProvider;

public class DashboardApplication {
    public static void main(String[] args) {
        SalesService salesService = new SalesService(
                new Sales(
                        MinuteBucketsProvider.createBuckets(),
                        new SalesAmountBucket()
                )
        );

        salesService.startBucketsTimer(new TimeProvider());

        new DashboardController(salesService);
    }
}
