
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositMoney extends JFrame {

    private JTextField customerNameField;
    private JPasswordField passwordField;
    private JTextField amountField;
    private JButton depositButton;

    public DepositMoney() {
        setTitle("Deposit Money");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
       getContentPane().setBackground(new Color(136, 0, 27));


        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setForeground(Color.white);
        customerNameLabel.setFont(new Font("Serif",Font.PLAIN,22));
        customerNameLabel.setBounds(300, 200, 170, 30);
        add(customerNameLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(500, 200, 350, 30);
        add(customerNameField);


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif",Font.PLAIN,22));
        passwordLabel.setForeground(Color.white);
        passwordLabel.setBounds(300, 280, 170, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(500, 280, 350, 30);
        add(passwordField);


        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.white);
        amountLabel.setFont(new Font("Serif",Font.PLAIN,22));
        amountLabel.setBounds(300, 360, 170, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(500, 360, 350, 30);
        add(amountField);


        depositButton = new JButton("Deposit");
        depositButton.setBounds(580, 550, 100, 30);
        add(depositButton);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer=Validation.searchCustomer(customerNameField.getText());
                if (customerNameField.getText().isEmpty()||passwordField.getText().isEmpty()||amountField.getText().isEmpty()||!Restaurant.chargeAccount(customer,amountField.getText(),passwordField.getText())) {
                    JOptionPane.showMessageDialog(null,"Invalid name or password");
                } else {
                    JOptionPane.showMessageDialog(null,"charged successfully");
                }
            }
        });
        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 610, 100, 30);
        add(prevButton);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeFrame employeeFrame =new EmployeeFrame();
            }
        });

        //الخلفية
        String imagePath = "src/imgs/bg12.jpg";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 1200, 700);
        add(label);

        setVisible(true);
    }

}

