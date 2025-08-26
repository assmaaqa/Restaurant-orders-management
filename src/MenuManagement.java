import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuManagement extends JFrame {

    private JButton addMeal = new JButton("Add Meal");
    private JButton editMeal =new JButton("Edit Meal");
    private JButton deleteMeal =new JButton("Delete Meal");
    private JButton save =new JButton("Save");
    private JButton hide =new JButton("Hide");
    private JPanel addMealPannel =new JPanel();
    private JPanel p2=new JPanel();
    private JPanel editMealPannel =new JPanel();
    private JPanel deleteMealPannel =new JPanel();
    private JTextField mealName =new JTextField();
    private JTextField ingredients =new JTextField();
    private JTextField editIngredients =new JTextField();
    private JTextField price =new JTextField();
    private JTextField editPrice =new JTextField();
    private JLabel lI=new JLabel("Ingredients:");
    private JLabel lP=new JLabel("Price:");
    private JScrollPane scrollPane=new JScrollPane();
    private JScrollPane scrollPane2=new JScrollPane();
    private Actionss action=new Actionss();
    private ArrayList<Recipe> deletedRecipes;

    private Recipe selectedRecipe;
    public MenuManagement(){




        setTitle("Menu Managment");
        setVisible(true);
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Color r =new Color(95, 35, 47, 255);


        // اضافة وجبة
        addMeal.setBounds(200,100,300,100);
        Color color2=new Color(255, 244, 243);
        addMeal.setBackground(color2);
        addMeal.setForeground(r);
        Font f1 = new Font("Arial", Font.BOLD, 40);
        addMeal.setFont(f1);

        addMealPannel.setBounds(600,80 , 480,140);
        addMealPannel.setLayout(null);
        addMealPannel.setBackground(color2);

        JLabel l1=new JLabel("Meal Name:");
        Font f3=new Font("Arial",Font.PLAIN ,15);
        l1.setFont(f3);
        l1.setForeground(r);
        l1.setBounds(20,15 , 100,25);
        mealName.setBounds(112,15,150,25);
        JLabel l2=new JLabel("Ingredients:");
        l2.setBounds(20 ,55 , 100,25);
        l2.setFont(f3);
        l2.setForeground(r);
        ingredients.setBounds(112,55,340,25);
        JLabel l3=new JLabel("Price:");
        l3.setBounds(20 ,95 , 100,25);
        l3.setFont(f3);
        l3.setForeground(r);
        price.setBounds(112,95,100,25);
        addMealPannel.add(l1);
        addMealPannel.add(mealName);
        addMealPannel.add(l2);
        addMealPannel.add(ingredients);
        addMealPannel.add(l3);
        addMealPannel.add(price);
        addMealPannel.setVisible(false);
        add(addMealPannel);


        //تعديل وجبة
        editMeal.setBounds(200,270,300,100);
        editMeal.setBackground(color2);
        editMeal.setForeground(r);
        editMeal.setFont(f1);
        p2.setBackground(color2);
        p2.setLayout(null);
        ButtonGroup group = new ButtonGroup();
        int i=0;
        for (Recipe recipe:Restaurant.getRecipes()) {
            JRadioButton mealRadioButton= new JRadioButton((i + 1) + ") " + recipe.getName());
            mealRadioButton.setFont(new Font("Arial", Font.PLAIN, 24));
            mealRadioButton.setForeground(new Color(95, 35, 47, 255));
            mealRadioButton.setBounds(50, 30 + (i * 100), 200, 30);

            mealRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { selectedRecipe = recipe; // تخزين الزر المحدد
                    swichToEditPannel();
                }
            });

            group.add(mealRadioButton);
            p2.add(mealRadioButton);
            i++;
        }
        p2.setPreferredSize(new Dimension(325, Restaurant.getRecipes().size() * 100));
        scrollPane = new JScrollPane(p2);
        scrollPane.setBounds(600,265 , 480,110);
        add(scrollPane);
        scrollPane.setVisible(false);
        editMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(true); 
            }
        });

        add(editMeal);

        // بانل التعديل
        editMealPannel.setBounds(600,265,480,110);
        editMealPannel.setBackground(color2);
        editMealPannel.setVisible(false);
        editMealPannel.setLayout(null);
        editIngredients.setBounds(112,15,340,25);
        lI.setBounds(20 ,15 , 100,25);
        lI.setFont(f3);
        lI.setForeground(r);
        editMealPannel.add(lI);
        editMealPannel.add(editIngredients);
        lP.setBounds(20 ,70 , 100,25);
        lP.setFont(f3);
        lP.setForeground(r);
        editMealPannel.add(lP);
        editPrice.setBounds(112,70,100,25);
        editMealPannel.add(editPrice);
        this.add(editMealPannel);

        //حذف وجبة
        deleteMeal.setBounds(200,440,300,100);
        deleteMeal.setBackground(color2);
        deleteMeal.setForeground(r);
        deleteMeal.setFont(f1);
        deleteMealPannel.setBackground(color2);
        deleteMealPannel.setLayout(null);
        deletedRecipes = new ArrayList<>();
        i=0;
        for (Recipe recipe:Restaurant.getRecipes()) {
            JRadioButton mealRadioButton= new JRadioButton((i + 1) + ") " + recipe.getName());
            mealRadioButton.setFont(new Font("Arial", Font.PLAIN, 24));
            mealRadioButton.setForeground(new Color(95, 35, 47, 255));
            mealRadioButton.setBounds(50, 30 + (i * 100), 200, 30);
            mealRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deletedRecipes.add(recipe);
                }
            });
            deleteMealPannel.add(mealRadioButton);
            i++;
        }
        deleteMealPannel.setPreferredSize(new Dimension(325, Restaurant.getRecipes().size() * 100));
        scrollPane2 = new JScrollPane(deleteMealPannel);
        scrollPane2.setBounds(600,435, 480,110);
        add(scrollPane2);
        scrollPane2.setVisible(false);
        //زر الحفظ والاخفاء
        save.setBounds(1050,600,100,30);
        save.setBackground(color2);
        save.setForeground(r);
        Font f2=new Font("Arial",Font.PLAIN,20);
        save.setFont(f2);
        hide.setBounds(930,600,100,30);
        hide.setBackground(color2);
        hide.setForeground(r);
        hide.setFont(f2);


        add(addMeal);add(editMeal);add(deleteMeal);add(save);add(hide);

        addMeal.addActionListener(action);
        editMeal.addActionListener(action);
        deleteMeal.addActionListener(action);
        hide.addActionListener(action);
        save.addActionListener(action);

        JButton reportsButton=new JButton("The Reports");
        reportsButton.setBounds(550,600,150,30);
        reportsButton.setBackground(color2);
        reportsButton.setForeground(r);
        reportsButton.setFont(f2);
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportsFrame reportsFrame=new ReportsFrame();
            }
        });
        add(reportsButton);

        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 600, 100, 30);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerFrame managerFrame=new ManagerFrame();
            }
        });
        add(prevButton);
        getContentPane().setBackground(r);
    }



    private void swichToEditPannel(){
        scrollPane.setVisible(false);
        editMealPannel.setVisible(true);
    }


    private class Actionss implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()== addMeal) {
                addMealPannel.setVisible(true);
            }
            if (e.getSource()== editMeal) {
                scrollPane.setVisible(true);
            }
            if (e.getSource()== deleteMeal) {
                scrollPane2.setVisible(true);
            }

            if (e.getSource()== hide) {
                scrollPane2.setVisible(false);
                scrollPane.setVisible(false);
                addMealPannel.setVisible(false);
                editMealPannel.setVisible(false);
            }
            if (e.getSource()==save){
                if (addMealPannel.isVisible()) {
                    try {
                        if (mealName.getText().isEmpty() || price.getText().isEmpty() || ingredients.getText().isEmpty() || !Restaurant.addRecipe(mealName.getText(), "src/imgs/newMeal.jpg", price.getText(), ingredients.getText())) {
                            throw new InvalidPriceException();
                        }
                    } catch (InvalidPriceException ipe) {
                        JOptionPane.showMessageDialog(null, "invalid input,or recipe already added");
                    } catch (RecipeNotFoundException rnfe) {
                        JOptionPane.showMessageDialog(null, "invalid input,or recipe already added");
                    }
                }
                if (editMealPannel.isVisible()){
                    try {
                        if (editIngredients.getText().isEmpty()||editPrice.getText().isEmpty()) {
                            throw new InvalidPriceException();
                        }
                        Restaurant.editMeal(selectedRecipe,editPrice.getText(),editIngredients.getText());
                    } catch (InvalidPriceException ipe) {
                        //error message
                        JOptionPane error = new JOptionPane();
                        error.setBounds(100, 100, 100, 100);
                        JOptionPane.showMessageDialog(null, "invalid input");
                        add(error);
                    }
                }
                if(deleteMealPannel.isVisible()){
                    for(Recipe recipe:deletedRecipes) {
                        Restaurant.deleteRecipe(recipe);
                    }
                }
                JOptionPane.showMessageDialog(null,"Done..");
            }
        }
    }
    public static void main(String[] args) {
        MenuManagement menuManagement=new MenuManagement();



    }
}
