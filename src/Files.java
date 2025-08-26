import java.io.File;
import java.io.IOException;

public class Files {
    public static File ordersFile = new File("ordersFile.bin");
    public static File customersFile = new File("customersFile.bin");
    public static File employeesFile = new File("employeesFile.bin");
    public static File recipesFile = new File("recipesFile.bin");
    public static File managerPassword = new File("managerPassword.bin");
    public static void createFiles(){
        try {
            ordersFile.createNewFile();
            customersFile.createNewFile();
            employeesFile.createNewFile();
            recipesFile.createNewFile();
            managerPassword.createNewFile();
        } catch (IOException e) {
        }
    }
}
