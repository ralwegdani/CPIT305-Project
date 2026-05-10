
import javax.swing.*;
import java.awt.*;

public class PassengerProfile {

    public static void main(String[] args) {
        // For standalone testing only
        showProfile(new User("Test", "User", "test@test.com", "P123456"));
    }

    static void showProfile(User user) {
        JFrame frame = new JFrame("My Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel main = new JPanel(null);
        main.setBackground(Color.WHITE);
        frame.setContentPane(main);

        // Header
        JPanel header = new JPanel(null);
        header.setBackground(new Color(0x2E4156));
        header.setBounds(0, 0, 420, 55);
        main.add(header);

        JLabel title = new JLabel("My Profile");
        title.setFont(new Font("SansSerif", Font.BOLD, 15));
        title.setForeground(Color.WHITE);
        title.setBounds(20, 15, 200, 25);
        header.add(title);

        // Avatar with initials
        String initials = user.getF_name().substring(0, 1).toUpperCase()
                + user.getL_name().substring(0, 1).toUpperCase();
        JLabel avatar = new JLabel(initials, SwingConstants.CENTER);
        avatar.setFont(new Font("SansSerif", Font.BOLD, 18));
        avatar.setForeground(Color.WHITE);
        avatar.setOpaque(true);
        avatar.setBackground(new Color(0x4A6FA5));
        avatar.setBounds(20, 75, 50, 50);
        main.add(avatar);

        // Name
        JLabel lblName = new JLabel(user.getF_name() + " " + user.getL_name());
        lblName.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblName.setForeground(new Color(0x263238));
        lblName.setBounds(82, 80, 250, 20);
        main.add(lblName);

        JLabel lblSub = new JLabel("Registered Passenger");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblSub.setForeground(new Color(0x90A4AE));
        lblSub.setBounds(82, 102, 200, 15);
        main.add(lblSub);

        // Separator
        JSeparator sep1 = new JSeparator();
        sep1.setForeground(new Color(0xE0E0E0));
        sep1.setBounds(20, 140, 380, 10);
        main.add(sep1);

        // Info rows
        addInfoRow(main, "Email", user.getEmail(), 155);
        addInfoRow(main, "Passport", user.getPassportNumber(), 195);
        addInfoRow(main, "User ID", String.valueOf(user.getUserID()), 235);

        // Separator
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(0xE0E0E0));
        sep2.setBounds(20, 275, 380, 10);
        main.add(sep2);

        // Tickets section
        JLabel ticketsTitle = new JLabel("My Tickets");
        ticketsTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
        ticketsTitle.setForeground(new Color(0x263238));
        ticketsTitle.setBounds(20, 290, 200, 20);
        main.add(ticketsTitle);

        JLabel noTickets = new JLabel("No tickets yet — book a flight to see history.");
        noTickets.setFont(new Font("SansSerif", Font.PLAIN, 12));
        noTickets.setForeground(new Color(0xB0BEC5));
        noTickets.setBounds(20, 315, 380, 20);
        main.add(noTickets);

        frame.setVisible(true);
    }

    private static void addInfoRow(JPanel main, String key, String value, int y) {
        JLabel keyLabel = new JLabel(key);
        keyLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        keyLabel.setForeground(new Color(0x90A4AE));
        keyLabel.setBounds(20, y, 90, 20);
        main.add(keyLabel);

        JLabel valLabel = new JLabel(value != null ? value : "—");
        valLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        valLabel.setForeground(new Color(0x263238));
        valLabel.setBounds(120, y, 260, 20);
        main.add(valLabel);
    }
}