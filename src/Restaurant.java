import java.io.PushbackInputStream;
import java.io.Serializable;
import java.util.*;

public class Restaurant implements Serializable {
    private static final long serialVersionUID =2L;
    private final static String name="restaurant";
    private static Manager manager=new Manager();
    private static ArrayList<Employee> employees=new ArrayList<>();
    private static Queue<Order> orders=new LinkedList<>();
    private static Queue<Order> inpreparationOrders=new LinkedList<>();
    private static PriorityQueue<Recipe> recipes=new PriorityQueue<>();
    private static PriorityQueue<Customer> customers=new PriorityQueue<>();

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static Queue<Order> getOrders() {
        return orders;
    }

    public static PriorityQueue<Recipe> getRecipes() {
        return recipes;
    }

    public static PriorityQueue<Customer> getCustomers() {
        return customers;
    }

    public static Employee signUpEmployee(String name, String address, String password){
        if(Validation.usernameValidation(name,false)!=null) return null;
        if(!password.equals(Employee.getPassword())){
            return null;
        }
        Employee employee=new Employee(name,address);
        WriteOnFiles.write(employee);
        employees.add(employee);

        return employee;
    }
    public static Customer signUpCustomer(String name, String address, String password){
        if(Validation.usernameValidation(name,true)!=null) return null;
        Customer customer=new Customer(name,address,password);
        WriteOnFiles.write(customer);
        customers.add(customer);
        return customer;
    }
    public static Employee logInEmployee(String username, String password){
        Employee account=Validation.searchEmployee(username);
        if (account==null){
            //username not found enter again
            return null;
        }
        if(!Validation.checkPassword(password,account)){
            return null;
        }
        return account;
    }
    public static Customer logInCustomer(String username, String password){
        Customer account=Validation.searchCustomer(username);
        if (account==null){
            //username not found enter again
            return null;
        }
        if(!Validation.checkPassword(password,account)){
            return null;
        }
        return account;
    }
    public static boolean logInManager(String password){
        if(password.equals(Manager.getPassword())){
            return true;
        }
        return false;
    }
    public static boolean changeManager(String newPassword, String oldPassword) {
        if(oldPassword.equals(Manager.getPassword())){
            WriteOnFiles.managerPassword(newPassword);
            Manager.setPassword(newPassword);
            return true;
        }
        return false;
    }
    public static boolean addRecipe(String name,String imagePath,String price,String ingredients) throws RecipeNotFoundException,InvalidPriceException{
        try {
            Validation.checkPrice(price);
            Validation.searchRecipe(name);
            return false;
        } catch (RecipeNotFoundException e){
        } catch (InvalidPriceException e){
            throw new InvalidPriceException();
        } catch (NumberFormatException e){
        }
        Recipe recipe=new Recipe(name,imagePath,price,ingredients,true);
        WriteOnFiles.write(recipe);
        Restaurant.recipes.add(recipe);
        return true;
    }
    public static void deleteRecipe(Recipe sentRecipe){
        Restaurant.recipes.remove(sentRecipe);
        WriteOnFiles.rewrite(sentRecipe);
    }
    public static void editMeal(Recipe sentRecipe,String price,String ingredients) throws InvalidPriceException {
        try {
            Validation.checkPrice(price);
        } catch (InvalidPriceException | NumberFormatException e) {
            throw new InvalidPriceException();
        }
        sentRecipe.setIngredients(ingredients);
        sentRecipe.setPrice(price);
        WriteOnFiles.rewrite(sentRecipe);
    }
    public static Order addOrder(ArrayList<Recipe> recipes, Customer customer, String dateOfDelivery,OrderType type,String details){
        Date validDateOfDelivery=Validation.dateValidation(dateOfDelivery);
        double price=calculatePrice(recipes);
        if (validDateOfDelivery == null) return null;
        Order order = new Order(recipes,customer,validDateOfDelivery,OrderStatus.INPREPERATION,type,price,details);
        customer.increaseOrdersCounter();
        customer.addOrder(order);
        WriteOnFiles.rewrite(customer);
        WriteOnFiles.rewrite(recipes.getFirst());
        WriteOnFiles.write(order);
        Restaurant.getOrders().add(order);
        return order;
    }
    public static double calculatePrice(ArrayList<Recipe> recipes){
        double totalPrice=0;
        for(Recipe recipe:recipes){
            try {
                totalPrice+=Double.parseDouble(recipe.getPrice());
            } catch (NumberFormatException e) {

            }
        }
        return totalPrice;
    }
    public static boolean chargeAccount(Customer customer,String amount,String password) throws NumberFormatException{
        if (!Validation.checkPassword(password,customer)) return false;
        try {
            Validation.checkPrice(String.valueOf(amount));
        } catch (InvalidPriceException |NumberFormatException e) {
            return false;
        }
        try {
            customer.chargeAccount(Double.parseDouble(amount));
        } catch (NumberFormatException e) {

        }
        WriteOnFiles.rewrite(customer);
        return true;
    }
    public static ArrayList<Order> showCustomerCurrentOrders(Customer customer){
        ArrayList<Order> currentOrders=new ArrayList<>();
        for (Order order:customer.getOrders()){
            if (order.getStatus().equals(OrderStatus.INPREPERATION)){
                currentOrders.add(order);
            }
        }
        return currentOrders;
    }
    public static void cancelOrder(Order order){
        if (!order.getStatus().equals(OrderStatus.INPREPERATION)) return;
        order.setStatus(OrderStatus.CANCELED);
        for (Recipe recipe: order.getRecipes()){
            recipe.decriesCounter();
        }
        WriteOnFiles.rewrite(order.getRecipes().getFirst());
        WriteOnFiles.rewrite(order);
        WriteOnFiles.rewrite(order.getCustomer());
    }
    public static void passOrder(){
        try {
            Order order=Restaurant.inpreparationOrders.peek();
            order.setStatus(OrderStatus.FINISHED);
            WriteOnFiles.rewrite(order);
            WriteOnFiles.rewrite(order.getCustomer());
        } catch (NullPointerException e) {

        }
    }
    public static void pay(Order order,double finalAmount){
        order.setPaid();
        order.getCustomer().withdrawAmount(finalAmount);
        Restaurant.inpreparationOrders.add(order);
        WriteOnFiles.rewrite(order);
        WriteOnFiles.rewrite(order.getCustomer());
    }
    public static void payCash(Order order){
        order.setPaid();
        WriteOnFiles.rewrite(order);
        WriteOnFiles.rewrite(order.getCustomer());
    }
    public static int dailyOrdersCount(){
        int ordersCounter=0;
        for (Order order:Restaurant.getOrders()){
            if (order.getDateOfOrder().after(new Date())){
                return ordersCounter;
            }
            ordersCounter++;
        }
        return ordersCounter;
    }
    public static Recipe mostOrderedRecipe(){
        try {
            return Restaurant.getRecipes().peek();
        } catch (NullPointerException e) {
            return null;
        }
    }
    public static Customer mostOrderingCustomer(){
        try {
            return Restaurant.getCustomers().peek();
        } catch (NullPointerException e){
            return null;
        }
    }
    public static double dailyReturns(){
        double returns=0;
        Iterator<Order> orderIterator=Restaurant.getOrders().iterator();
        while (orderIterator.hasNext()){
            Order order=orderIterator.next();
            if (Validation.compareDates(order.getDateOfOrder(),new Date())){
                returns+=order.getTotalPrice();
            }
        }
        return returns;
    }


}
