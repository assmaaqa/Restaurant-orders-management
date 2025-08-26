import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

    public Login() {

        setTitle("Login");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);


        JLabel loginLabel = new JLabel("Chose account type:");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setForeground(Color.white);
        loginLabel.setBounds(500, 200, 400, 100);
        add(loginLabel);


        JRadioButton customerButton = new JRadioButton("Customer");
        customerButton.setBounds(500, 300, 150, 30);

        JRadioButton employeeButton = new JRadioButton("Employee");
        employeeButton.setBounds(500, 350, 150, 30);

        JRadioButton managerButton = new JRadioButton("Manager");
        managerButton.setBounds(500, 400, 150, 30);

        ButtonGroup loginGroup = new ButtonGroup();
        loginGroup.add(customerButton);
        loginGroup.add(employeeButton);
        loginGroup.add(managerButton);

        add(customerButton);
        add(employeeButton);
        add(managerButton);


        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerFrame customerFrame=new CustomerFrame();
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeFrame employee = new EmployeeFrame();
            }
        });

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerFrame manager = new ManagerFrame();
            }
        });

        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 600, 100, 30);
        add(prevButton);


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Welcome welcome = new Welcome();
            }
        });

        String imagePath = "src/imgs/bg2.png";
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
