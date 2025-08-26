import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerFrame extends JFrame {
    private JButton changeManager = new JButton("Change Manager");
    private JButton save = new JButton("Save");
    private JButton login = new JButton("Login");
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPasswordField newPassword = new JPasswordField();
    private JPasswordField oldPassword = new JPasswordField();
    private JPasswordField managerPassword = new JPasswordField();

    public ManagerFrame() {
        // login
        JLabel l1 = new JLabel("Welcome");
        JLabel l2 = new JLabel("Enter Password Please:");
        JLabel l3 = new JLabel("Manager Login");
        Actionss action = new Actionss();


        setTitle("Manager");
        setVisible(true);
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Color r = new Color(95, 35, 47, 255);
        //   getContentPane().setBackground(r);
        l1.setBounds(450, 100, 600, 100);
        l1.setForeground(Color.WHITE);
        Font f1 = new Font("Arial", Font.PLAIN, 70);
        l1.setFont(f1);
        l2.setBounds(465, 270, 300, 100);
        l2.setForeground(Color.WHITE);
        Font f2 = new Font("Arial", Font.PLAIN, 25);
        l2.setFont(f2);
        managerPassword.setBounds(465, 380, 260, 50);
        login.setBounds(555, 500, 100, 50);
        login.setBackground(Color.white);
        login.setForeground(r);
        Font f3 = new Font("Arial", Font.PLAIN, 22);
        login.setFont(f3);
        changeManager.setBounds(950, 570, 200, 60);
        changeManager.setBackground(r);
        changeManager.setForeground(Color.WHITE);
        Font f4 = new Font("Arial", Font.PLAIN, 20);
        changeManager.setFont(f4);
        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 600, 100, 30);
        p1.setBounds(0, 0, 1200, 700);
        p1.setBackground(r);
        p1.setLayout(null);
        this.add(p1);
        p1.setVisible(true);
        p1.add(prevButton);
        p1.add(l1);
        p1.add(l2);
        p1.add(managerPassword);
        p1.add(login);
        p1.add(changeManager);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
            }
        });
        login.addActionListener(action);

        //change
        JLabel l4 = new JLabel("DO YOU WANT TO CHANGE THE MANAGER?");
        JLabel l5 = new JLabel("Enter old password please:");
        JLabel l6 = new JLabel("Enter new password please:");
        JButton prevButton2 = new JButton("Previous");
        prevButton2.setBounds(20, 600, 100, 30);

        p2.setLayout(null);
        p2.setVisible(false);
        p2.setBounds(0, 0, 1200, 700);
        p2.setBackground(r);
        l4.setBounds(30, 10, 900, 50);
        l4.setForeground(Color.WHITE);
        Font f5 = new Font("Arial", Font.ITALIC + Font.BOLD, 35);
        l4.setFont(f5);
        l5.setBounds(230, 200, 400, 50);
        l5.setForeground(Color.WHITE);
        l5.setFont(f2);
        oldPassword.setBounds(580, 200, 280, 50);
        l6.setBounds(230, 400, 400, 50);
        l6.setForeground(Color.WHITE);
        l6.setFont(f2);
        newPassword.setBounds(580, 400, 280, 50);
        save.setBounds(580, 550, 100, 50);
        save.setBackground(Color.white);
        save.setFont(f3);
        save.setForeground(r);
        p2.add(prevButton2);
        p2.add(l4);
        p2.add(l5);
        p2.add(oldPassword);
        p2.add(l6);
        p2.add(newPassword);
        p2.add(save);

        prevButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setVisible(true);
            }
        });
        this.add(p2);
        changeManager.addActionListener(action);
        save.addActionListener(action);

    }

    private void swichToP2() {
        p1.setVisible(false);
        p2.setVisible(true);
    }

    private void swichToP1() {
        p1.setVisible(true);
        p2.setVisible(false);
    }

    private class Actionss implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(changeManager)) {
                swichToP2();
            }
            if (e.getSource().equals(login)) {
                if (managerPassword.getText().isEmpty() || !Restaurant.logInManager(managerPassword.getText())) {
                    JOptionPane error = new JOptionPane();
                    error.setBounds(100, 100, 100, 100);
                    JOptionPane.showMessageDialog(null, "passsword incorrect");
                    add(error);
                } else {
                    //go to menuManagement
                    MenuManagement menuManagement = new MenuManagement();
                }
            }

            if (e.getSource().equals(save)) {
                if (newPassword.getText().isEmpty() || oldPassword.getText().isEmpty() || !Restaurant.changeManager(newPassword.getText(), oldPassword.getText())) {
                    JOptionPane error = new JOptionPane();
                    error.setBounds(100, 100, 100, 100);
                    JOptionPane.showMessageDialog(null, "passsword incorrect");
                    add(error);
                } else {
                    swichToP1();
                    managerPassword.setText("");
                }
            }

        }


    }

    public static void main(String[] args) {
        ManagerFrame m1 = new ManagerFrame();
    }
}