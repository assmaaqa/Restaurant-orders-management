import javax.swing.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Files.createFiles();
        ReadFiles.read();
        SwingUtilities.invokeLater(() -> {
            Welcome welcome=new Welcome();
        });

    }
}
// try {
//Recipes:
//        Restaurant.addRecipe("Kabsa","src/imgs/Kabsa.png","10", "Yellow Rice , Chicken, Kabsa sauce, Mixed spice");
//            Restaurant.addRecipe("Kebab","src/imgs/Kebab.png","20","Grilled meat, Grilled onion, Grilled tomato, Biwaz");
//            Restaurant.addRecipe("Arabic shawarma","src/imgs/Arabic shawarma.png","8","Shawarma sandwich, mayonnaise,French fries plate ");
//            Restaurant.addRecipe("Kibbeh","src/imgs/Kibbeh.png","15","Fried Kibbeh, mitbil, Tabbouleh, Soup");
//            Restaurant.addRecipe("Falafel","src/imgs/Falafel.png","1","8 pieces of falafel, tomato slices, pickles");
//        } catch (InvalidPriceException e) {
//        throw new RuntimeException(e);
//        }

//customers: assmaa/aa/aa       rafah/rr/rr
//orders: assmaa/cash/delivered/falafel/1      assmaa/card/inpreparation/shawarma/8      rafah/cash/delivered/falafel/1     rafah/cash/delivered/falafel/1     rafah/cash/delivered/falafel/1
//mostRecipe: falafel            returns:10          mostCus: assmaa         dailyOrders: 3
//employees: emp1/empPass
//manager: mm