import controller.DashboardController;
import model.Sales;
import service.TimeProvider;
import service.TransactionService;

public class DashboardApplication {


    public static void main(String[] args) {
        new DashboardController(
                new TransactionService(
                        new Sales(),
                        new TimeProvider()
                )
        );
    }


}
