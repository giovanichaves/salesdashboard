import controller.DashboardController;
import model.Sales;
import model.SalesAmountBucket;
import service.SalesService;
import service.TransactionService;
import utils.TimeProvider;

public class DashboardApplication {


    public static void main(String[] args) {
        new DashboardController(
                new TransactionService(
                        new Sales(
                            SalesService.bucketsProvider(),
                            new SalesAmountBucket()
                        ),
                        new TimeProvider()
                )
        );
    }


}
