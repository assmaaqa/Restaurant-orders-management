
import com.sun.jdi.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderForm extends JFrame {



    HashMap<JCheckBox,Recipe> checkedRecipes;
    public OrderForm(Customer customer) {


        setTitle("Order Form");
        setVisible(true);
        setSize(1210, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Choose your order:");
        titleLabel.setBounds(80, 80, 200, 50);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));
        titleLabel.setForeground(Color.white);
        add(titleLabel);

        JPanel mealsPanel = new JPanel();
        mealsPanel.setLayout(null);
        mealsPanel.setBackground(new Color(255, 255, 255));

        checkedRecipes=new HashMap<>();
        int i=0;
        for (Recipe recipe:Restaurant.getRecipes()) {
            JCheckBox mealCheckBox = new JCheckBox((i + 1) + ") " + recipe.getName());
            mealCheckBox.setFont(new Font("Arial", Font.PLAIN, 24));
            mealCheckBox.setForeground(new Color(136, 0, 27));
            mealCheckBox.setBounds(50, 30 + (i * 100), 200, 30);
            //mealsPanel.add(mealCheckBox); كأنو هي زيادة؟؟
            checkedRecipes.put(mealCheckBox,recipe);

//            mealCheckBox.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                        checkedRecipes.add(mealCheckBox);
//                }
//            });
            mealsPanel.add(mealCheckBox);
            i++;
        }

        mealsPanel.setPreferredSize(new Dimension(325, Restaurant.getRecipes().size() * 100));

        JScrollPane scrollPane = new JScrollPane(mealsPanel);
        scrollPane.setBounds(10, 130, 425, 350);

        add(scrollPane);



        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 610, 100, 30);
        add(prevButton);


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecipesList recipesList = new RecipesList(customer);
            }
        });


//        JButton paymentButton = new JButton("Payment");
//        paymentButton.setBounds(1050, 610, 100, 30);
//        add(paymentButton);
//
//        paymentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                PaymentFrame paymentFrame = new PaymentFrame(customer);
//            }
//        });

        //قسم تاريخ التسليم
        JTextField dateOfDeliveryField = new JTextField(30);
        JLabel lb3 = new JLabel("Enter the date");
        lb3.setForeground(new Color(128, 128, 128));
        lb3.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn3 = new JPanel();
        add(pn3);
        GridBagConstraints grid3 = new GridBagConstraints();
        design(dateOfDeliveryField, lb3, pn3, grid3);
        dateOfDeliveryField.setBounds(800, 405, 300, 50);
        dateOfDeliveryField.setBackground(Color.white);
        add(dateOfDeliveryField);

        //قسم التفاصيل
        JTextField detailsField = new JTextField(30);
        JLabel lb4 = new JLabel("Enter the details of order (optional)");
        lb4.setForeground(new Color(128, 128, 128));
        lb4.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn4 = new JPanel();
        add(pn4);
        GridBagConstraints grid4 = new GridBagConstraints();
        design(detailsField, lb4, pn4, grid4);
        detailsField.setBounds(800, 475, 300, 50);
        detailsField.setBackground(Color.white);
        add(detailsField);


        // قسم النوع
        Font font2 = new Font("Serif", Font.BOLD, 26);
        JLabel typeLabel = new JLabel("The order is:");
        typeLabel.setFont(font2);
        typeLabel.setForeground(Color.white);
        typeLabel.setBounds(25, 500, 200, 30);

        JRadioButton internalType = new JRadioButton("inside");
        internalType.setBounds(25, 540, 100, 20);

        JRadioButton deliveryType = new JRadioButton("Delivery");
        deliveryType.setBounds(150, 540, 100, 20);


        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(internalType);
        typeGroup.add(deliveryType);


        add(typeLabel);
        add(internalType);
        add(deliveryType);
        JButton nextToPayment = new JButton("next");
        nextToPayment.setBounds(1000, 610, 175, 30);
        add(nextToPayment);

        nextToPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Recipe> recipes=new ArrayList<>();
                checkedRecipes.forEach((Key, Value)->{
                    try {
                        if (Key.isSelected()) recipes.add(Validation.searchRecipe(Value.getName()));
                    } catch (RecipeNotFoundException ex) {

                    }
                });

                OrderType type;
                if (internalType.isSelected()) type=OrderType.INTERNAL;
                else if (deliveryType.isSelected()) type=OrderType.DELIVERY;
                else type=null;
                Order order=Restaurant.addOrder(recipes,customer,dateOfDeliveryField.getText(),type,detailsField.getText());
                if(type==null||checkedRecipes.isEmpty()||dateOfDeliveryField.getText().isEmpty()||order==null){
                    JOptionPane error=new JOptionPane();
                    error.setBounds(100,100,100,100);
                    JOptionPane.showMessageDialog(null,"invalid date or required field is empty or you don't have money:( ");
                    add(error);
                    if (order!=null){
                        Restaurant.cancelOrder(order);
                    }
                } else {
                    PaymentFrame paymentFrame = new PaymentFrame(order);
                }
            }
        });


        //الخلفية
        String imagePath = "src/imgs/bg6.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 1200, 700);
        add(label);


    }

    private void design(JTextField customerNameField, JLabel lb1, JPanel pn1, GridBagConstraints grid) {
        pn1.setPreferredSize(new Dimension(490, 490));
        pn1.setLayout(new GridBagLayout());
        grid.insets = new Insets(5, 5, 5, 5);
        grid.gridx = 0;
        pn1.add(customerNameField);
        customerNameField.setLayout(new FlowLayout());
        customerNameField.add(lb1);
        customerNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent arg0) {
                if (arg0.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    lb1.setVisible(false);
                } else {
                    if (customerNameField.getText().equals("")) {
                        lb1.setVisible(true);
                    }
                }
            }


        });
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //OrderForm orderForm = new OrderForm(new Customer("mm","mm","jj"));
        });
    }
}