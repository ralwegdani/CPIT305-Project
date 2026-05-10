
import javax.swing.*;
import java.awt.*;

public class Home {

    public static void main(String[] args) {
        // For standalone testing only
        showHome(new User("Test", "User", "test@test.com", "P123456"));
    }

    static void showHome(User user) {
        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel main = new JPanel(null);
        main.setBackground(new Color(0xD4D8DD));
        frame.setContentPane(main);

        JLabel title = new JLabel("Airline Ticket Reservation", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(0x1A2D42));
        title.setBounds(0, 30, 400, 30);
        main.add(title);

        String welcomeText = "Welcome, " + user.getF_name() + "!";
        JLabel welcome = new JLabel(welcomeText, SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.PLAIN, 14));
        welcome.setForeground(new Color(0x2E4156));
        welcome.setBounds(0, 65, 400, 20);
        main.add(welcome);

        JButton profileBtn = makeButton("Profile");
        profileBtn.setBounds(60, 120, 280, 45);
        main.add(profileBtn);

        JButton searchBtn = makeButton("Search for Flights");
        searchBtn.setBounds(60, 185, 280, 45);
        main.add(searchBtn);

        JButton reservationBtn = makeButton("Book a Flight");
        reservationBtn.setBounds(60, 250, 280, 45);
        main.add(reservationBtn);

        JButton logoutBtn = makeButton("Logout");
        logoutBtn.setBackground(new Color(0x8B0000));
        logoutBtn.setBounds(60, 315, 280, 45);
        main.add(logoutBtn);

        frame.setVisible(true);

        // --- Button Actions ---

        profileBtn.addActionListener(e -> {
            PassengerProfile.showProfile(user);
        });

        searchBtn.addActionListener(e -> {
            showFlightSearch(frame, user);
        });

        reservationBtn.addActionListener(e -> {
            new ReservationFrame(user).setVisible(true);
        });

        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
                login.showLogin();
            }
        });
    }

    private static void showFlightSearch(JFrame parent, User user) {
        JDialog dialog = new JDialog(parent, "Search Flights", true);
        dialog.setSize(400, 220);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(new Color(0xD4D8DD));

        JLabel lbl = new JLabel("Enter Flight ID:");
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setForeground(new Color(0x1A2D42));
        lbl.setBounds(60, 30, 200, 25);
        dialog.add(lbl);

        JTextField flightIdField = new JTextField();
        flightIdField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        flightIdField.setBounds(60, 60, 280, 30);
        dialog.add(flightIdField);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        resultLabel.setForeground(new Color(0x1A2D42));
        resultLabel.setBounds(30, 100, 340, 20);
        dialog.add(resultLabel);

        JButton searchBtn = makeButton("Search");
        searchBtn.setBounds(60, 130, 280, 40);
        dialog.add(searchBtn);

        searchBtn.addActionListener(e -> {
            String flightId = flightIdField.getText().trim();
            if (flightId.isEmpty()) {
                resultLabel.setText("Please enter a Flight ID.");
                return;
            }
            try {
                BookingManager manager = new BookingManager();
                String result = manager.searchFlight(flightId);
                resultLabel.setText(result);
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        dialog.setVisible(true);
    }

    private static JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(new Color(0x1A2D42));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        return btn;
    }
}