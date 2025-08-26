import java.io.*;
import java.util.Date;
import java.util.HashMap;

public class ReadFiles {
    public static FileInputStream fileInputStream;
    public static ObjectInputStream objectInputStream;
    public static void read(){
        File file;
        try {
            file=Files.ordersFile;
            fileInputStream = new FileInputStream(file);
            objectInputStream=new ObjectInputStream(fileInputStream);
            while (fileInputStream.available()!=0){
                Restaurant.getOrders().add((Order) objectInputStream.readObject());
            }
        }
        catch (IOException e){
        }catch (ClassNotFoundException e){
        }
        try {
            file = Files.customersFile;
            fileInputStream = new FileInputStream(file);
            objectInputStream=new ObjectInputStream(fileInputStream);
            while (fileInputStream.available()!=0) {
                Restaurant.getCustomers().add((Customer) objectInputStream.readObject());
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        try {
            file = Files.employeesFile;
            fileInputStream = new FileInputStream(file);
            objectInputStream=new ObjectInputStream(fileInputStream);
            while (fileInputStream.available()!=0) {
                Restaurant.getEmployees().add((Employee) objectInputStream.readObject());
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        try {
            file = Files.recipesFile;
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() != 0) {
                Restaurant.getRecipes().add((Recipe) objectInputStream.readObject());
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        try {
            file = Files.managerPassword;
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Manager.setPassword(objectInputStream.readObject().toString());
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }

    }
}
