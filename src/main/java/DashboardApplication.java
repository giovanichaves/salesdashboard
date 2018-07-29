import controller.DashboardController;
import model.Sales;
import service.SalesService;
import utils.TimeProvider;
import service.TransactionService;

public class DashboardApplication {


    public static void main(String[] args) {
        new DashboardController(
                new TransactionService(
                        new Sales(SalesService.bucketsProvider()),
                        new TimeProvider()
                )
        );
    }


}
