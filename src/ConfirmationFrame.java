import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConfirmationFrame extends JFrame {
    
    private final Color PRIMARY_DARK = Color.decode("#1A2D42");
    private final Color LIGHT_GRAY = Color.decode("#D4D8DD");

    public ConfirmationFrame() {
        setTitle("Booking Confirmation");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(LIGHT_GRAY);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        //
        JLabel msg = new JLabel("<html><center>Reservation<br>Confirmed!</center></html>", SwingConstants.CENTER);
        msg.setFont(new Font("SansSerif", Font.BOLD, 22));
        msg.setForeground(PRIMARY_DARK);
        panel.add(msg, BorderLayout.CENTER);

        //
        JButton closeButton = new JButton("Finish");
        closeButton.setBackground(Color.decode("#276e9e")); 
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setFocusPainted(false);
        closeButton.setOpaque(true); 
        closeButton.setBorderPainted(false);
        
        
        panel.add(closeButton, BorderLayout.SOUTH);
        
        add(panel);
        
        closeButton.addActionListener(e -> System.exit(0));
    }   
}