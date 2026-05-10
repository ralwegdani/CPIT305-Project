
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost";
    private static final int    PORT = 5500;

    public static String send(Object request) throws Exception {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream  in  = new ObjectInputStream(socket.getInputStream())) {
            out.writeObject(request);
            out.flush();
            Object response = in.readObject();
            if (response instanceof String) return (String) response;
            throw new Exception("Unexpected response type from server.");
        }
    }

    public static String searchFlight(String flightId) throws Exception {
        return send("SEARCH:" + flightId);
    }

    // FIX: now includes seatType and seatNumber in the request
    public static String bookFlight(String flightId, String seatType, String seatNumber) throws Exception {
        return send("BOOK:" + flightId + ":" + seatType + ":" + seatNumber);
    }

    public static String registerUser(User user) throws Exception {
        return send(user);
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== Search Test ===");
            System.out.println(searchFlight("FL123"));

            System.out.println("\n=== Book Test ===");
            System.out.println(bookFlight("FL123", "economy", "12A"));

            System.out.println("\n=== Search After Book ===");
            System.out.println(searchFlight("FL123"));

        } catch (Exception e) {
            System.err.println("Could not connect to the server. Is it running?");
            e.printStackTrace();
        }
    }
}