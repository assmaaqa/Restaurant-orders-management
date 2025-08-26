import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WriteOnFiles {

    public static FileOutputStream fileOutputStream;
    public static void managerPassword(String password){
        try {
            new FileOutputStream("managerPassword.bin").close();
        } catch (IOException e) {
        }
        try {
            fileOutputStream=new FileOutputStream(Files.managerPassword,true);
        } catch (FileNotFoundException e) {
        }
        ObjectOutputStream objectOutputStream= null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
        }
        try {
            objectOutputStream.writeObject(password);
            objectOutputStream.close();
        } catch (IOException e) {
        }
    }
    public static <T> void write(T object){
        File file=null;

        if (object instanceof Order){
            try {
                file=Files.ordersFile;
                fileOutputStream = new FileOutputStream(file,true);
            } catch (FileNotFoundException e) {
            }
        } else if (object instanceof Customer) {
            try {
                file = Files.customersFile;
                fileOutputStream = new FileOutputStream(file,true);
            } catch (FileNotFoundException e) {
            }
        } else if (object instanceof  Employee) {
            try {
                file = Files.employeesFile;
                fileOutputStream = new FileOutputStream(file,true);
            } catch (FileNotFoundException e) {
            }
        } else if (object instanceof Recipe) {
            try {
                file = Files.recipesFile;
                fileOutputStream = new FileOutputStream(file,true);
            } catch (FileNotFoundException e) {
            }
        }
        if(file.length()==0) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(object);
                objectOutputStream.close();
            } catch (IOException e) {
            }
        } else {
            try {
                RestaurantOutputStream outputStream = new RestaurantOutputStream(fileOutputStream);
                outputStream.writeObject(object);
                outputStream.close();
            } catch (IOException e) {
            }
        }

    }
    public static <T> void rewrite(T object){
        ObjectOutputStream objectOutputStream = null;
        if (object instanceof Recipe){
            try {
                FileOutputStream fileOutputStream =new FileOutputStream(Files.recipesFile);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } catch (IOException e) {
            }
            for (Recipe recipe:Restaurant.getRecipes()){
                try {
                    objectOutputStream.writeObject(recipe);
                } catch (IOException e) {
                }
            }
        } else if (object instanceof Customer){
            try {
                FileOutputStream fileOutputStream =new FileOutputStream(Files.customersFile);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } catch (IOException e) {
            }
            for (Customer customer:Restaurant.getCustomers()){
                try {
                    objectOutputStream.writeObject(customer);
                } catch (IOException e) {
                }
            }
            try {
                objectOutputStream.close();
            } catch (IOException e) {
            }
        } else if(object instanceof Order){
            try {
                FileOutputStream fileOutputStream =new FileOutputStream(Files.ordersFile);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } catch (IOException e) {
            }
            for (Order order:Restaurant.getOrders()){
                try {
                    objectOutputStream.writeObject(order);
                } catch (IOException e) {
                }
            }
            try {
                objectOutputStream.close();
            } catch (IOException e) {
            }
        }
    }


}
