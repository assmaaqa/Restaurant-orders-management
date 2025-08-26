import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID =2L;
    private static String password="empPass";
    private String name;
    private String address;
    public Employee(String name,String address){
        this.name=name;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public static void setPassword(String oldPassword, String password) {
        if(oldPassword.equals(Employee.password))
            Employee.password = password;
    }

    public static String getPassword() {
        return password;
    }
}
