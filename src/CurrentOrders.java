import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrentOrders extends JFrame {
    private JLabel titleLabel;

    public CurrentOrders() {
        setTitle("Current Order");
        setVisible(true);
        setSize(1210, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("Current Orders List");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setBounds(525, 120, 250, 30);
        add(titleLabel);

        createOrderPanel();

        JButton passButton = new JButton("Pass Order");
        passButton.setBounds(1050, 610, 100, 30);
        add(passButton);

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Restaurant.passOrder();
                CurrentOrders currentOrders=new CurrentOrders();
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
        JButton chargeButton=new JButton("charge money");
        chargeButton.setBounds(570,610,125,30);
        add(chargeButton);

        chargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositMoney depositMoney=new DepositMoney();
            }
        });

        // الخلفية
        String imagePath = "src/imgs/bg8.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 1200, 700);
        add(label);
    }

    private void createOrderPanel() {
        JPanel currentOrderPanel = new JPanel();
        currentOrderPanel.setLayout(null);
        currentOrderPanel.setBackground(new Color(255, 255, 255));

        int index = 0;
        for (Order order : Restaurant.getOrders()) {

            JLabel currentLabel = new JLabel(order.getId() + ") " + order.getCustomer().getName());
            currentLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            currentLabel.setForeground(new Color(136, 0, 27));
            currentLabel.setBounds(20, 30 + (index * 100), 250, 30);
            currentOrderPanel.add(currentLabel);

            JLabel statueLabel =new JLabel(String.valueOf(order.getStatus()));
            statueLabel.setBounds(175,30+(index * 100),100,30);


            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(300, 30 + (index * 100), 90, 30);
            index++;
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Restaurant.cancelOrder(order);
                    updateOrderPanel();
                }
            });
            currentOrderPanel.add(statueLabel);
            currentOrderPanel.add(cancelButton);

        }

        currentOrderPanel.setPreferredSize(new Dimension(325, Restaurant.getOrders().size() * 100));

        JScrollPane scrollPane = new JScrollPane(currentOrderPanel);
        scrollPane.setBounds(420, 170, 425, 350);

        add(scrollPane);
    }

    private void updateOrderPanel() {
        getContentPane().removeAll();
        add(titleLabel);
        createOrderPanel();
        addButtonsAndBackground();

        revalidate();
        repaint();
    }

    private void addButtonsAndBackground() {
        JButton passButton = new JButton("Pass Order");
        passButton.setBounds(1050, 610, 100, 30);
        add(passButton);

        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 610, 100, 30);
        add(prevButton);

        JButton chargeButton=new JButton("charge money");
        chargeButton.setBounds(600,610,125,30);
        add(chargeButton);

        // الخلفية
        String imagePath = "src/imgs/bg8.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 1200, 700);
        add(label);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrentOrders currentOrders = new CurrentOrders();
        });
    }
}
