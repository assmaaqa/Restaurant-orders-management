import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeeFrame extends JFrame {

    public EmployeeFrame(){

        setTitle("Employee");
        setSize(1200,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);


        JTextField employeeNameField = new JTextField();
        JLabel lb1 = new JLabel("Enter the username");
        lb1.setForeground(new Color(128, 128, 128));
        lb1.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn1 = new JPanel();
        add(pn1);
        GridBagConstraints grid1 = new GridBagConstraints();
        design(employeeNameField, lb1, pn1, grid1);
        employeeNameField.setBounds(380, 200, 400, 50);
        // Color beige = new Color(255, 239, 204);
        employeeNameField.setBackground(Color.white);
        add(employeeNameField);


        JPasswordField employeePasswordField = new JPasswordField();
        JLabel lb2 = new JLabel("Enter the password");
        lb2.setForeground(new Color(128, 128, 128));
        lb2.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn2 = new JPanel();
        add(pn2);
        GridBagConstraints grid2 = new GridBagConstraints();
        design(employeePasswordField, lb2, pn2, grid2);
        employeePasswordField.setBounds(380,270,400,50);
        employeePasswordField.setBackground(Color.white);
        add(employeePasswordField);

        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.black);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBounds(430, 340, 300, 40);
        add(loginButton);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee=Restaurant.logInEmployee(employeeNameField.getText(),employeePasswordField.getText());
                if (employeeNameField.getText().isEmpty()||employeePasswordField.getText().isEmpty()||employee==null) {
                    JOptionPane error = new JOptionPane();
                    error.setBounds(100, 100, 100, 100);
                    JOptionPane.showMessageDialog(null, "username or passsword incorrect");
                    add(error);
                }else {
                    CurrentOrders currentOrders=new CurrentOrders();
                }
            }
        });

        JLabel label1 = new JLabel("you don't have account? ");
        label1.setFont(new Font("Arial", Font.PLAIN, 16));
        label1.setForeground(Color.white);
        label1.setBounds(400, 400, 180, 30);
        add(label1);

        JButton button1 = new JButton("Sing up");
        button1.setBounds(600, 400, 100, 30);
        button1.setContentAreaFilled(false);
        button1.setForeground(Color.white);
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterEmployee registerE=new RegisterEmployee();
            }
        });


        JButton submitButton=new JButton("Submit");
        submitButton.setBounds(555,350,80,30);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentOrders currentOrders=new CurrentOrders();
            }
        });

        add(submitButton);


        JButton prevButton=new JButton("Previous");
        prevButton.setBounds(20,600,100,30);
        add(prevButton);


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login=new Login();
            }
        });

        String imagePath = "src/imgs/bg7.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 1200, 700);
        add(label);


    }
    private void design(JTextField employeePasswordField, JLabel lb1, JPanel pn1, GridBagConstraints grid) {
        pn1.setPreferredSize(new Dimension(490, 490));
        pn1.setLayout(new GridBagLayout());
        grid.insets = new Insets(5, 5, 5, 5);
        grid.gridx = 0;
        pn1.add(employeePasswordField);
        employeePasswordField.setLayout(new FlowLayout());
        employeePasswordField.add(lb1);
        employeePasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent arg0) {
                if (arg0.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    lb1.setVisible(false);
                } else {
                    if (employeePasswordField.getText().equals("")) {
                        lb1.setVisible(true);
                    }
                }
            }


        });
    }
}
