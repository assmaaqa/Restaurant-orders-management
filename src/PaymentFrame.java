import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class PaymentFrame extends JFrame {
    private JTextField totalAmountField;
    private JTextField tipField;
    private JCheckBox cashCheckBox;
    private JCheckBox cardCheckBox;
    private JButton payButton;
    private JLabel totalLabel;
    private JLabel tipLabel;

    public PaymentFrame(Order order) {
        setTitle("Payment Frame");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        totalLabel = new JLabel("Total Amount:");
        Font f1 = new Font("Serif", Font.BOLD, 24);
        totalLabel.setFont(f1);
        totalLabel.setForeground(Color.white);
        totalLabel.setBounds(900, 200, 200, 30);
        add(totalLabel);
        totalAmountField = new JTextField();
        totalAmountField.setBounds(900, 275, 235, 30);
        totalAmountField.setEditable(false);
        add(totalAmountField);

        tipLabel = new JLabel("Tip (optional):");
        tipLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        tipLabel.setForeground(Color.white);
        tipLabel.setBounds(900, 330, 125, 30);
        add(tipLabel);

        tipField = new JTextField();
        tipField.setBounds(1035, 330, 100, 30);
        add(tipField);

        cashCheckBox = new JCheckBox("Pay Cash");
        cashCheckBox.setBounds(175, 500, 120, 30);
        add(cashCheckBox);

        cardCheckBox = new JCheckBox("Pay by Card");
        cardCheckBox.setBounds(525, 500, 120, 30);
        add(cardCheckBox);

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(cashCheckBox);
        typeGroup.add(cardCheckBox);


        totalAmountField.setText(String.valueOf(order.getTotalPrice()));
        payButton = new JButton("Pay");
        payButton.setBounds(575, 600, 75, 25);
        add(payButton);


        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (order.isPaid()) throw new InvalidPriceException();
                    double totalAmount = Double.parseDouble(totalAmountField.getText());
                    double tip = tipField.getText().isEmpty() ? 0 : Double.parseDouble(tipField.getText());
                    Validation.checkPrice(String.valueOf(tip));
                    double finalAmount = totalAmount + tip;

                    if (cashCheckBox.isSelected()) {
                        JOptionPane.showMessageDialog(null, "You choose to pay cash. Total amount: $" + finalAmount);
                        startOrderProcessingThread(order);
                        order.setStatus(OrderStatus.DELIVERED);
                        Restaurant.payCash(order);
                        WriteOnFiles.rewrite(order);
                    } else if (cardCheckBox.isSelected()) {
                        String cardNumber = JOptionPane.showInputDialog(null, "Enter your card number:", "Credit Card Payment", JOptionPane.PLAIN_MESSAGE);
                        if (cardNumber != null && !cardNumber.isEmpty() && cardNumber.equals(order.getCustomer().getPassword())&&order.getCustomer().getAccount()>=finalAmount) {
                            JOptionPane.showMessageDialog(null, "Payment process completed successfully by card. Total amount: $" + finalAmount);
                            Restaurant.pay(order,finalAmount);
                        } else {
                            JOptionPane.showMessageDialog(null, "Payment cancelled or card number not provided or you don't have money.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please choose a payment method.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid format. Please enter a valid number for the tip.");
                } catch (InvalidPriceException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid price. Please enter a valid number for the tip, or you have already paid");
                }
            }
        });

        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(20, 610, 100, 30);
        add(prevButton);


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderForm orderForm = new OrderForm(order.getCustomer());
            }
        });


        //الخلفية
        String imagePath = "src/imgs/bg10.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 1200, 700);
        add(label);

        setVisible(true);
    }
    private void startOrderProcessingThread(Order order) {
        Thread orderProcessingThread = new Thread(() -> {
            try {

                order.setStatus(OrderStatus.INPREPERATION);
                WriteOnFiles.rewrite(order);
                showStatusNotification(" your order in preparation...", 2);


                TimeUnit.SECONDS.sleep(1);


                order.setStatus(OrderStatus.FINISHED);
                WriteOnFiles.rewrite(order);
                showStatusNotification("your order is finished!", 2);

            } catch (InterruptedException e) {

            }
        });
        orderProcessingThread.start();
    }
    private void showStatusNotification(String message, int delayInSeconds) {
        SwingUtilities.invokeLater(() -> {

            JDialog dialog = new JDialog();
            dialog.setTitle("Order Statue");
            dialog.setModal(false);
            dialog.setLayout(new BorderLayout());


            JLabel label = new JLabel(message, SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 16));
            dialog.add(label, BorderLayout.CENTER);


            dialog.setSize(300, 100);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);


            dialog.setVisible(true);


            Timer timer = new Timer(delayInSeconds * 1000, e -> dialog.dispose());
            timer.setRepeats(false);
            timer.start();
        });
    }

}
