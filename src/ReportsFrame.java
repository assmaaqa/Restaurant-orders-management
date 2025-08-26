import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsFrame extends JFrame {
    JLabel numberL = new JLabel("Number Of Daily Orders:");
    JTextField dailyOrdersNumberT = new JTextField();
    JLabel revenuesL = new JLabel("Daily Revenues:");
    JTextField dailyReturnsT = new JTextField();
    JLabel mostPopularRecipeL = new JLabel("Most Popular Recipe:");
    JTextField mostPopularRecipeT = new JTextField();
    JLabel mostFrequentCustomerL = new JLabel("Most Frequent Customer:");
    JTextField mostFrequentCustomerT = new JTextField();

    JPanel p1 = new JPanel();

    public  ReportsFrame() {
        setTitle("Reports");
        setVisible(true);
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Color r1 = new Color(95, 35, 47);
        Color r2=new Color(255, 244, 243);
        p1.setBounds(0,0,1200,700);
        p1.setLayout(null);
        this.add(p1);
        numberL.setBounds(800,470,250,30);
        numberL.setBackground(r1);
        Font f1=new Font("Arial", Font.PLAIN, 20);
        numberL.setForeground(Color.WHITE);
        numberL.setFont(f1);
        p1.add(numberL);
        dailyOrdersNumberT.setBounds(850,515,100,30);
        dailyOrdersNumberT.setBackground(r2);
        dailyOrdersNumberT.setText(String.valueOf(Restaurant.dailyOrdersCount()));
        dailyOrdersNumberT.setEditable(false);
        p1.add(dailyOrdersNumberT);

        revenuesL.setBounds(230,470,200,30);
        revenuesL.setBackground(r1);
        revenuesL.setForeground(Color.WHITE);
        revenuesL.setFont(f1);
        p1.add(revenuesL);
        dailyReturnsT.setBounds(245,515,100,30);
        dailyReturnsT.setBackground(r2);
        dailyReturnsT.setText(String.valueOf(Restaurant.dailyReturns()));
        dailyReturnsT.setEditable(false);
        p1.add(dailyReturnsT);

        mostFrequentCustomerL.setBounds(200,170,250,30);
        mostFrequentCustomerL.setBackground(r1);
        mostFrequentCustomerL.setForeground(Color.WHITE);
        mostFrequentCustomerL.setFont(f1);
        p1.add(mostFrequentCustomerL);
        mostFrequentCustomerT.setBounds(210,215,200,30);
        mostFrequentCustomerT.setBackground(r2);
        mostFrequentCustomerT.setText(Restaurant.mostOrderingCustomer().getName());
        mostFrequentCustomerT.setEditable(false);
        p1.add(mostFrequentCustomerT);

        mostPopularRecipeL.setBounds(810,170,250,30);
        mostPopularRecipeL.setBackground(r1);
        mostPopularRecipeL.setForeground(Color.WHITE);
        mostPopularRecipeL.setFont(f1);
        p1.add(mostPopularRecipeL);
        mostPopularRecipeT.setBounds(810,215,190,30);
        mostPopularRecipeT.setBackground(r2);
        mostPopularRecipeT.setText(Restaurant.mostOrderedRecipe().getName());
        mostPopularRecipeT.setEditable(false);
        p1.add(mostPopularRecipeT);

        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 600, 100, 30);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuManagement menuManagement=new MenuManagement();
            }
        });

        p1.add(prevButton);

        p1.setBackground(r1);



    }



public static void main(String[] args) {
    ReportsFrame r1 = new ReportsFrame();
}
}
