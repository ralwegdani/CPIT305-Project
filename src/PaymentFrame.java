import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaymentFrame extends JFrame {

    private final Color PRIMARY_DARK = Color.decode("#1A2D42"); 
    private final Color LIGHT_GRAY = Color.decode("#D4D8DD"); 

    public PaymentFrame() {
        setTitle("Secure Payment");
        setSize(450, 500); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(LIGHT_GRAY);
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Payment Details", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(PRIMARY_DARK);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; 
        mainPanel.add(title, gbc);

        gbc.gridwidth = 2; 
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Cardholder Name:"), gbc); 
        gbc.gridy = 2;
        mainPanel.add(new JTextField(20), gbc);

        gbc.gridy = 3;
        mainPanel.add(new JLabel("Card Number:"), gbc); 
        gbc.gridy = 4;
        mainPanel.add(new JTextField(20), gbc);

        gbc.gridwidth = 1; 
        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(new JLabel("Expiry (MM/YY):"), gbc); 
        gbc.gridy = 6;
        mainPanel.add(new JTextField(10), gbc); 

        gbc.gridx = 1; gbc.gridy = 5;
        mainPanel.add(new JLabel("CVV:"), gbc); 
        gbc.gridy = 6;
        mainPanel.add(new JTextField(5), gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2; 
        gbc.insets = new Insets(30, 0, 10, 0); 
        JButton payBtn = new JButton("Pay & Confirm");
        payBtn.setBackground(Color.decode("#276e9e")); 
        payBtn.setForeground(Color.WHITE);
        payBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        payBtn.setOpaque(true); 
        payBtn.setBorderPainted(false);
        mainPanel.add(payBtn, gbc); 
        

        add(mainPanel);
        
        payBtn.addActionListener(e -> {
            new ConfirmationFrame().setVisible(true);
            dispose();
        });
    }
}

