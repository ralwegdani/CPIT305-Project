import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock; // NEW**

public class BookingManager {
    private Map<String, Integer> flightEconomySeats  = new HashMap<>();
    private Map<String, Integer> flightBusinessSeats = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock(true); // NEW**

    public BookingManager() {
        try {
            ResultSet rs = DB.getInstance().retrive(
                    "SELECT flight_id, total_e_seats, total_b_seats FROM flights"
            );
            while (rs.next()) {
                String id = rs.getString("flight_id");
                flightEconomySeats.put(id,  rs.getInt("total_e_seats"));
                flightBusinessSeats.put(id, rs.getInt("total_b_seats"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to load flights from DB: " + e.getMessage());
        }
    }

    // removed synchronized replaced with ReentrantLock 
    public String bookSeat(String flightId, User passenger, String seatType, String seatNumber) {
        lock.lock(); // NEW**
        try {
            // Check adult
            if (!passenger.isAdult())
                return "ERROR: Passenger must be 18 or older to book a seat.";

            // Check flight exists
            if (!flightEconomySeats.containsKey(flightId))
                return "ERROR: Flight " + flightId + " not found.";

            if (seatType.equalsIgnoreCase("economy")) {
                int available = flightEconomySeats.get(flightId);
                if (available <= 0)
                    return "FAILED: No available economy seats on flight " + flightId;
                try {
                    Economey economy = new Economey(flightId);
                    economy.SetSeatNumber("BOOK");
                    flightEconomySeats.put(flightId, available - 1);
                    String ticketId = UUID.randomUUID().toString();
                    Ticket ticket = new Ticket(ticketId, passenger, flightId,
                            seatNumber, "Economy", String.valueOf(economy.getPrice()));
                    ticket.save();
                    System.out.println("[BOOKED] " + passenger.getF_name()
                            + " booked ECONOMY on " + flightId
                            + " | Remaining: " + (available - 1));
                    ticket.printInvoice();
                    return "SUCCESS: Economy seat booked for " + passenger.getF_name() + " on flight " + flightId;
                } catch (SQLException e) {
                    return "DATABASE ERROR: " + e.getMessage();
                }

            } else if (seatType.equalsIgnoreCase("business")) {
                int available = flightBusinessSeats.get(flightId);
                if (available <= 0)
                    return "FAILED: No available business seats on flight " + flightId;
                try {
                    Business business = new Business(flightId);
                    business.SetSeatNumber("BOOK");
                    flightBusinessSeats.put(flightId, available - 1);
                    String ticketId = UUID.randomUUID().toString();
                    Ticket ticket = new Ticket(ticketId, passenger, flightId,
                            seatNumber, "Business", String.valueOf(business.getPrice()));
                    ticket.save();
                    System.out.println("[BOOKED] " + passenger.getF_name()
                            + " booked BUSINESS on " + flightId
                            + " | Remaining: " + (available - 1));
                    ticket.printInvoice();
                    return "SUCCESS: Business seat booked for " + passenger.getF_name() + " on flight " + flightId;
                } catch (SQLException e) {
                    return "DATABASE ERROR: " + e.getMessage();
                }

            } else {
                return "ERROR: Invalid seat type '" + seatType + "'. Choose 'economy' or 'business'.";
            }

        } finally {
            lock.unlock(); // NEW**
        }
    }

    public String searchFlight(String flightId) {       // مهمة لان كلاينت هاندلر يناديها
        if (!flightEconomySeats.containsKey(flightId))
            return "Flight " + flightId + " not found.";
        int e = flightEconomySeats.get(flightId);
        int b = flightBusinessSeats.get(flightId);
        return "Flight " + flightId
                + " | Economy seats: " + e
                + " | Business seats: " + b
                + " | Total available: " + (e + b);
    }
}

   /* public String searchFlight(String flightId) {

        try {
            ResultSet rs = DB.getInstance().retrive(
                    "SELECT (total_b_seats + total_e_seats) AS total FROM flights " +
                            "WHERE flight_id = '" + flightId + "'"
            );
            if (rs.next()) {
                flightSeats.put(flightId, rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.err.println("Search error: " + e.getMessage());
        }

        if (!flightSeats.containsKey(flightId)) {
            return "Flight " + flightId + " not found.";
        }

        int seats = flightSeats.get(flightId);
        return "Flight " + flightId + " | Available seats: " + seats;
    }**/

