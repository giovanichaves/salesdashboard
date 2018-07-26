import controller.Dashboard;
import service.TransactionService;

public class DashboardApplication {


    public static void main(String[] args) {
        new Dashboard(new TransactionService());
    }


}
