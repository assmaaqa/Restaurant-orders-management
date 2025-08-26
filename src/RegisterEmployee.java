
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterEmployee extends JFrame {

    public RegisterEmployee(){
        setTitle("Register");
        setSize(1200,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        JTextField customerNameField = new JTextField();
        JLabel lb1 = new JLabel("Enter the username");
        lb1.setForeground(new Color(128, 128, 128));
        lb1.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn1 = new JPanel();
        add(pn1);
        GridBagConstraints grid1 = new GridBagConstraints();
        design(customerNameField, lb1, pn1, grid1);
        customerNameField.setBounds(155, 120, 350, 50);
        customerNameField.setBackground(Color.white);
        add(customerNameField);


        JTextField addressField = new JTextField(30);
        JLabel lb2 = new JLabel("Enter the address");
        lb2.setForeground(new Color(128, 128, 128));
        lb2.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn2 = new JPanel();
        add(pn2);
        GridBagConstraints grid2 = new GridBagConstraints();
        design(addressField, lb2, pn2, grid2);
        addressField.setBounds(155, 205, 350, 50);
        addressField.setBackground(Color.white);
        add(addressField);

        JPasswordField passwordField = new JPasswordField(30);
        JLabel lb3 = new JLabel("Enter the password");
        lb3.setForeground(new Color(128, 128, 128));
        lb3.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel pn3 = new JPanel();
        add(pn3);
        GridBagConstraints grid3 = new GridBagConstraints();
        design(passwordField, lb3, pn3, grid3);
        passwordField.setBounds(155, 290, 350, 50);
        passwordField.setBackground(Color.white);
        add(passwordField);


        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(275, 390, 80, 30);

        add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee=Restaurant.signUpEmployee(customerNameField.getText(),addressField.getText(),passwordField.getText());
                if (customerNameField.getText().isEmpty()||addressField.getText().isEmpty()||passwordField.getPassword().length==0||employee==null){
                    //Rafah error code
                    JOptionPane error=new JOptionPane();
                    error.setBounds(100,100,100,100);
                    JOptionPane.showMessageDialog(null,"invalid username or password");
                    add(error);
                }else {
                    JOptionPane pane=new JOptionPane();
                    pane.setBounds(100,100,100,100);
                    JOptionPane.showMessageDialog(null,"Thank you for your time");
                    add(pane);
                    EmployeeFrame employeeFrame=new EmployeeFrame();
                }
            }
        });

        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(35, 600, 100, 30);
        add(prevButton);


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeFrame employeeFrame=new EmployeeFrame();
            }
        });

        String imagePath = "src/imgs/bg9.png";
        ImageIcon imageIcon2 = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon2);
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
