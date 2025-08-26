import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable ,Comparable<Recipe> {
    private static final long serialVersionUID =2L;
    private String name;
    private String imagePath;
    private String price;
    private int ordersCounter;
    private String ingredients;

    public Recipe(String name) {
        this.name=name;
    }

    public String getImagePath() {
        return imagePath;
    }
    public Recipe(String name, String imagePath, String price, String ingredients, boolean available){
        this.name=name;
        this.imagePath=imagePath;
        this.price=price;
        this.ingredients=ingredients;
    }

    public String getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public int getOrdersCounter() {
        return ordersCounter;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void decriesCounter(){
        this.ordersCounter--;
    }

    public void setOrdersCounter(int ordersCounter) {
        this.ordersCounter = ordersCounter;
    }

    @Override
    public int compareTo(Recipe o) {
        return Integer.compare(o.ordersCounter,this.ordersCounter);
    }
}
