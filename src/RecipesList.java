import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class RecipesList extends JFrame {

    private JPanel recipeDetailsPanel;
    private JPanel orderDetailsPanel;
    private JLabel mealImageLabel;
    private JLabel ingredientsLabel;
    private JLabel priceLabel;


    public RecipesList(Customer customer) {


        setTitle("Recipe List");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color r = new Color(136, 0, 27);
        getContentPane().setBackground(r);
        setLayout(null);

        JLabel titleLabel = new JLabel("MENU");
        titleLabel.setBounds(100, 70, 200, 50);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(Color.white);
        add(titleLabel);


        JPanel mealsPanel = new JPanel();
        mealsPanel.setLayout(null);
        mealsPanel.setBackground(new Color(255, 255, 255));

        int i=0;
        for (Recipe recipe:Restaurant.getRecipes()) {
            JLabel mealLabel = new JLabel((i + 1) + ") " + recipe.getName());
            mealLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            mealLabel.setForeground(new Color(136, 0, 27));
            mealLabel.setBounds(50, 30 + (i * 100), 150, 30);
            mealsPanel.add(mealLabel);

            JButton mealButton = new JButton("Details");
            mealButton.setBounds(200, 30 + (i * 100), 90, 30);
            mealButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showMealDetails(recipe);
                }
            });
            mealsPanel.add(mealButton);
            i++;
        }


        mealsPanel.setPreferredSize(new Dimension(325, Restaurant.getRecipes().size() * 100));


        JScrollPane scrollPane = new JScrollPane(mealsPanel);
        scrollPane.setBounds(10, 150, 325, 450);

        add(scrollPane);





        recipeDetailsPanel = new JPanel();
        recipeDetailsPanel.setLayout(null);
        recipeDetailsPanel.setBounds(400, 10, 520, 550);
        recipeDetailsPanel.setVisible(false);


        mealImageLabel = new JLabel();
        mealImageLabel.setBounds(10, 50, 500, 500);
        ingredientsLabel = new JLabel();
        ingredientsLabel.setBounds(20, 10, 540, 70);
        priceLabel = new JLabel();
        priceLabel.setBounds(20, 70, 220, 30);

        recipeDetailsPanel.add(mealImageLabel);
        recipeDetailsPanel.add(ingredientsLabel);
        recipeDetailsPanel.add(priceLabel);

        add(recipeDetailsPanel);

        setVisible(true);

        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 610, 100, 30);
        add(prevButton);


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerFrame customerFrame = new CustomerFrame();
            }
        });

        JButton hideButton = new JButton("Hide");
        hideButton.setBounds(560, 610, 70, 30);
        add(hideButton);

        hideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recipeDetailsPanel.setVisible(false);
            }
        });




        JButton addOrderButton = new JButton("add order");
        addOrderButton.setBounds(1050, 610, 100, 30);
        add(addOrderButton);

        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderForm orderForm = new OrderForm(customer);
            }
        });

        //الخلفية
        String imagePath = "src/imgs/bg5.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 1200, 680);
        add(label);
        //
//        JOptionPane finished=new JOptionPane("finished",JOptionPane.INFORMATION_MESSAGE);
//        JDialog dialog=finished.createDialog("finished");
//        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//        dialog.setVisible(true);
//        new Thread(()->{
//            try {
//                Thread.sleep(2000);
//
//            } catch (InterruptedException e) {
//            }
//            dialog.dispose();
//        }).start();


    }


    private void showMealDetails(Recipe recipe) {
        mealImageLabel.setIcon(new ImageIcon(recipe.getImagePath()));
        ingredientsLabel.setText(String.valueOf(recipe.getIngredients()));
        priceLabel.setText(recipe.getPrice());
        recipeDetailsPanel.setVisible(true);
    }
    private void showMealDetails(Order order) {
        int i=0;
        for (Recipe recipe :order.getRecipes()){
            JLabel mealName=new JLabel();
            orderDetailsPanel.add(mealName);
            mealName.setBounds(20,30+(i*100),200,30);
            i++;
        }
        orderDetailsPanel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

        });
    }
}
