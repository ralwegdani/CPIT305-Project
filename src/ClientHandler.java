
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket         socket;
    private BookingManager bookingManager;

    public ClientHandler(Socket socket, BookingManager bookingManager) {
        this.socket         = socket;
        this.bookingManager = bookingManager;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream  in  = new ObjectInputStream(socket.getInputStream())) {

            Object request = in.readObject();

            if (request instanceof User) {
                DB.Add((User) request);
                out.writeObject("SUCCESS_REG");

            } else if (request instanceof String) {
                String reqStr = (String) request;

                if (reqStr.startsWith("BOOK:")) {
                    // FIX: format is now BOOK:<flightId>:<seatType>:<seatNumber>:<passengerName(serialized)>
                    // Client sends User object separately; here we use defaults for handler simplicity
                    String[] parts = reqStr.split(":", 4);
                    if (parts.length < 4 || parts[1].isBlank()) {
                        out.writeObject("ERROR: Invalid BOOK request. Expected BOOK:<flightId>:<seatType>:<seatNumber>");
                    } else {
                        String flightId   = parts[1].trim();
                        String seatType   = parts[2].trim();
                        String seatNumber = parts[3].trim();
                        // A default guest user — in a full system the client would send a User object
                        User guest = new User("Guest", "User", "guest@airline.com", "N/A", 25);
                        String result = bookingManager.bookSeat(flightId, guest, seatType, seatNumber);
                        out.writeObject(result);
                    }

                } else if (reqStr.startsWith("SEARCH:")) {
                    String[] parts = reqStr.split(":", 2);
                    if (parts.length < 2 || parts[1].isBlank()) {
                        out.writeObject("ERROR: Invalid SEARCH request. Expected SEARCH:<flightId>");
                    } else {
                        out.writeObject(bookingManager.searchFlight(parts[1].trim()));
                    }

                } else {
                    out.writeObject("ERROR: Unknown request: " + reqStr);
                }

            } else {
                out.writeObject("ERROR: Unrecognized request type.");
            }

            out.flush();

        } catch (Exception e) {
            System.err.println("Handler Error: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (Exception ignored) {}
        }
    }
}
