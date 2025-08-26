import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable ,Comparable<Customer> {
    private static final long serialVersionUID =2L;
    private String name;
    private ArrayList<Order> orders;
    private int ordersCounter;
    private String address;
    private String password;
    private double account;
    public Customer(String name, String address,String password){
        //ordersCounter++;
        this.name=name;
        this.orders=new ArrayList<>();
        this.address=address;
        this.password=password;
        this.account=0;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getOrdersCounter() {
        return ordersCounter;
    }
    public void increaseOrdersCounter(){
        this.ordersCounter++;
    }

    public double getAccount() {
        return account;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void chargeAccount(double amount) {
        this.account += amount;
    }
    public void withdrawAmount(double amount){
        this.account-=amount;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    @Override
    public int compareTo(Customer o) {
        return Integer.compare(o.ordersCounter,this.ordersCounter);
    }
}
