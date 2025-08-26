import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {
    public static Date dateValidation(String date) {
        DateFormat simpledateformat= new SimpleDateFormat("dd/MM/yyyy");
        simpledateformat.setLenient(false);
        try {
            Date formattedDate = simpledateformat.parse(date);
            if(formattedDate.before(new Date())) throw new ParseException("",0);
            return formattedDate;
        } catch (ParseException parseException){
            return null;
        }
    }

    public static Customer searchCustomer(String name){
            for (Customer customer:Restaurant.getCustomers()) {
                if (customer.getName().equals(name)) return customer;
            }
        return null;
    }
    public static Employee searchEmployee(String name){
            for (Employee employee:Restaurant.getEmployees()) {
                if (employee.getName().equals(name)) return employee;
            }
        return null;
    }
    public static <T> String usernameValidation(String name,boolean type){
        String valid=null;
        try {
            if(type){
                valid=searchCustomer(name).getName();
            }else {
                valid=searchEmployee(name).getName();
            }
        } catch (NullPointerException e) {
            return null;
        }
        return name;
    }
    public static <T> boolean checkPassword(String password,T sentAccount){
        String realPassword=null;
        if (sentAccount instanceof Customer){
            realPassword=((Customer) sentAccount).getPassword();
        } else if(sentAccount instanceof Employee){
            realPassword = Employee.getPassword();
        }else if (sentAccount instanceof Manager){
            realPassword=((Manager) sentAccount).getPassword();
        }
        if (!password.equals(realPassword)){
            return false;
        }

        return true;
    }
    public static void checkPrice(String price) throws InvalidPriceException,NumberFormatException {
        double doublePrice=Double.parseDouble(price);
        if(doublePrice<0) throw new InvalidPriceException();
    }
    public static Recipe searchRecipe(String name) throws RecipeNotFoundException {
        for(Recipe recipe:Restaurant.getRecipes()){
            if(name.equals(recipe.getName())) return recipe;
        }
        throw new RecipeNotFoundException();
    }
    public static boolean compareDates(Date firstDate,Date secondDate){
        if(firstDate.getYear()== secondDate.getYear() && firstDate.getMonth()== secondDate.getMonth() && firstDate.getDay()==secondDate.getDay())
            return true;
        return false;
    }

}
