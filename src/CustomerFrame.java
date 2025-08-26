import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomerFrame extends JFrame {
    public CustomerFrame() {
        setTitle("Customer");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);


        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 600, 100, 30);
        add(prevButton);


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
            }
        });


        JTextField customerNameField = new JTextField();
        JLabel lb1 = new JLabel("Enter the username");
        lb1.setForeground(new Color(128, 128, 128));
        lb1.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn1 = new JPanel();
        add(pn1);
        GridBagConstraints grid1 = new GridBagConstraints();
        design(customerNameField, lb1, pn1, grid1);
        customerNameField.setBounds(380, 180, 400, 50);
        // Color beige = new Color(255, 239, 204);
        customerNameField.setBackground(Color.white);
        add(customerNameField);


        JPasswordField customerPasswordField = new JPasswordField();
        JLabel lb2 = new JLabel("Enter the password");
        lb2.setForeground(new Color(128, 128, 128));
        lb2.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn2 = new JPanel();
        add(pn2);
        GridBagConstraints grid2 = new GridBagConstraints();
        design(customerPasswordField, lb2, pn2, grid2);
        customerPasswordField.setBounds(380, 240, 400, 50);
        Color beige = new Color(255, 239, 204);
        customerNameField.setBackground(Color.white);
        add(customerPasswordField);


        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.black);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBounds(430, 310, 300, 40);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer=Restaurant.logInCustomer(customerNameField.getText(),customerPasswordField.getText());
                if (customerNameField.getText().isEmpty()||customerPasswordField.getText().isEmpty()||customer==null){
                    JOptionPane error=new JOptionPane();
                    error.setBounds(100,100,100,100);
                    JOptionPane.showMessageDialog(null,"username or passsword incorrect");
                    add(error);
                } else{
                    RecipesList recipesList = new RecipesList(customer);
                }
            }
        });

        JLabel label1 = new JLabel("you don't have account? ");
        label1.setFont(new Font("Arial", Font.PLAIN, 16));
        label1.setForeground(Color.white);
        label1.setBounds(400, 385, 180, 30);
        add(label1);

        JButton button1 = new JButton("Sing up");
        button1.setBounds(600, 385, 100, 30);
        button1.setContentAreaFilled(false);
        button1.setForeground(Color.white);
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterCustomer register = new RegisterCustomer();
            }
        });

        String imagePath = "src/imgs/bg3.png";
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
}
