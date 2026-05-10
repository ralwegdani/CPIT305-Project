
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReservationFrame extends JFrame implements ActionListener {

    private JTextField tfFlightId, tfSeatType;
    private JButton btnConfirm;
    private User user;

    public ReservationFrame(User user) {
        super("Book a Flight");
        this.user = user;

        setSize(420, 380);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(null);
        main.setBackground(new Color(0xD4D8DD));
        setContentPane(main);

        // Title
        JLabel title = new JLabel("Flight Booking", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(0x1A2D42));
        title.setBounds(0, 20, 420, 30);
        main.add(title);

        // Show who is booking
        JLabel passengerInfo = new JLabel("Passenger: " + user.getF_name() + " " + user.getL_name(), SwingConstants.CENTER);
        passengerInfo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        passengerInfo.setForeground(new Color(0x2E4156));
        passengerInfo.setBounds(0, 55, 420, 20);
        main.add(passengerInfo);

        // Flight ID
        addLabel(main, "Flight ID:", 70, 100);
        tfFlightId = addField(main, 70, 123);

        // Seat Type
        addLabel(main, "Seat Type (economy / business):", 70, 170);
        tfSeatType = addField(main, 70, 193);

        // Status label
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setForeground(Color.RED);
        statusLabel.setBounds(40, 245, 340, 20);
        main.add(statusLabel);

        // Confirm button
        btnConfirm = new JButton("Confirm Booking");
        btnConfirm.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnConfirm.setBackground(new Color(0x1A2D42));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setBorderPainted(false);
        btnConfirm.setOpaque(true);
        btnConfirm.setBounds(70, 275, 280, 45);
        btnConfirm.addActionListener(this);
        main.add(btnConfirm);

        // store status label so action can update it
        btnConfirm.putClientProperty("statusLabel", statusLabel);
    }

    private void addLabel(JPanel p, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        lbl.setForeground(new Color(0x1A2D42));
        lbl.setBounds(x, y, 300, 20);
        p.add(lbl);
    }

    private JTextField addField(JPanel p, int x, int y) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tf.setBounds(x, y, 280, 30);
        p.add(tf);
        return tf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {
            confirmAction();
        }
    }

    void confirmAction() {
        JLabel statusLabel = (JLabel) btnConfirm.getClientProperty("statusLabel");
        String flightId = tfFlightId.getText().trim();
        String seatType = tfSeatType.getText().trim();

        if (flightId.isEmpty() || seatType.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        btnConfirm.setEnabled(false);
        btnConfirm.setText("Booking...");

        // Run network call off the EDT
        new Thread(() -> {
            try (Socket socket = new Socket("localhost", 5500);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                String passengerName = user.getF_name() + " " + user.getL_name();
                String data = "BOOK:" + flightId + ":" + passengerName;
                out.writeObject(data);
                out.flush();

                String response = (String) in.readObject();

                SwingUtilities.invokeLater(() -> {
                    if (response != null && response.startsWith("SUCCESS")) {
                        dispose();
                        new PaymentFrame().setVisible(true);
                    } else {
                        statusLabel.setText(response != null ? response : "Booking failed.");
                        btnConfirm.setEnabled(true);
                        btnConfirm.setText("Confirm Booking");
                    }
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Server unreachable. Please try again.");
                    btnConfirm.setEnabled(true);
                    btnConfirm.setText("Confirm Booking");
                });
                ex.printStackTrace();
            }
        }).start();
    }
}