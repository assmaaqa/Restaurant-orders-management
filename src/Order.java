import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID =2L;
    private int id;
    public static int idCounter;
    private ArrayList<Recipe> recipes;
    private Customer customer;
    private Date dateOfOrder;
    private Date dateOfDelivery;
    private OrderStatus status;
    private OrderType type;
    private double totalPrice;
    private boolean paid;
    private String details;
    public Order(ArrayList<Recipe>recipes,Customer customer,Date dateOfDelivery,OrderStatus status,OrderType type,double totalPrice,String details){
        idCounter++;
        id=idCounter;
        setRecipes(recipes);
        this.recipes=recipes;
        this.customer=customer;
        dateOfOrder=new Date();
        this.dateOfDelivery=dateOfDelivery;
        this.status=status;
        this.type=type;
        this.totalPrice=totalPrice;
        this.details=details;
        this.paid=false;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderType getType() {
        return type;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid() {
        this.paid = true;
    }
    public void setRecipes(ArrayList<Recipe> recipes){
        for (Recipe recipe:recipes){
            recipe.setOrdersCounter(recipe.getOrdersCounter()+1);
        }
    }

    public void setTip(double tip) {
        this.totalPrice +=tip;
    }
}
