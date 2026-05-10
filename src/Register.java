import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register {

    public static void main(String[] args) {
        showRegister();
    }

    static void showRegister() {
        JFrame frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel main = new JPanel(null);
        main.setBackground(new Color(0xD4D8DD));
        frame.setContentPane(main);

//title
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(0x1A2D42));
        title.setBounds(0, 20, 400, 30);
        main.add(title);
//_______________________________________________________
        // First Name field
        JLabel fLabel = new JLabel("First Name");
        fLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        fLabel.setForeground(new Color(0x1A2D42));
        fLabel.setBounds(60, 70, 150, 20);
        main.add(fLabel);

        JTextField firstField = new JTextField();
        firstField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        firstField.setBounds(60, 93, 280, 30);
        main.add(firstField);
//_______________________________________________________
        // Last Name field
        JLabel lLabel = new JLabel("Last Name");
        lLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        lLabel.setForeground(new Color(0x1A2D42));
        lLabel.setBounds(60, 135, 150, 20);
        main.add(lLabel);

        JTextField lastField = new JTextField();
        lastField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lastField.setBounds(60, 158, 280, 30);
        main.add(lastField);
//_______________________________________________________

        // Email field
        JLabel eLabel = new JLabel("Email");
        eLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        eLabel.setForeground(new Color(0x1A2D42));
        eLabel.setBounds(60, 200, 150, 20);
        main.add(eLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        emailField.setBounds(60, 223, 280, 30);
        main.add(emailField);
//_______________________________________________________

        // Password field
        JLabel pLabel = new JLabel("Password");
        pLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        pLabel.setForeground(new Color(0x1A2D42));
        pLabel.setBounds(60, 265, 150, 20);
        main.add(pLabel);

        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        passField.setBounds(60, 288, 280, 30);
        main.add(passField);

        // Password Confirmation
        JLabel pcLabel = new JLabel("Password Confirmation");
        pcLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        pcLabel.setForeground(new Color(0x1A2D42));
        pcLabel.setBounds(60, 330, 220, 20);
        main.add(pcLabel);

        JPasswordField passConfField = new JPasswordField();
        passConfField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        passConfField.setBounds(60, 353, 280, 30);
        main.add(passConfField);
//_______________________________________________________

        // Register Button
        JButton regBtn = new JButton("Register");
        regBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        regBtn.setBackground(new Color(0x1A2D42));
        regBtn.setForeground(Color.WHITE);
        regBtn.setFocusPainted(false);
        regBtn.setBorderPainted(false);
        regBtn.setBounds(60, 400, 280, 35);
        main.add(regBtn);
//_______________________________________________________

        // Already registered (back to login)
        JLabel alreadyLabel = new JLabel("you already registered?");
        alreadyLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        alreadyLabel.setForeground(new Color(0x1A2D42));
        alreadyLabel.setBounds(60, 450, 175, 20);
        main.add(alreadyLabel);

        JLabel loginLink = new JLabel("<html><u>Login</u></html>");
        loginLink.setFont(new Font("SansSerif", Font.BOLD, 13));
        loginLink.setForeground(new Color(0x1A2D42));
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLink.setBounds(238, 450, 50, 20);
        main.add(loginLink);

        frame.setVisible(true);
    }
}