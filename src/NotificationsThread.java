import javax.swing.*;

public class NotificationsThread extends Thread{
    @Override
    public void run() {
        JOptionPane.showMessageDialog(null,"your order is in his way to you");
        try {
            Thread.sleep(2000);
            Thread.interrupted();
        } catch (InterruptedException e) {
        }
    }
}
