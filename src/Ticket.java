
import java.sql.SQLException;
import java.util.UUID;

public class Ticket {
    private String ticketID;
    private User   user;
    private String flightId;
    private String seatNumber;
    private String seatType;
    private String seatPrice;
    DB db = DB.getInstance();

    // FIX: parameter was named "flights" but this.flightID was assigned from itself (null)
    public Ticket(String ticketID, User user, String flightId,
                  String seatNumber, String seatType, String seatPrice) {
        this.ticketID   = ticketID;
        this.user       = user;
        this.flightId   = flightId;   // FIX: now correctly assigned from parameter
        this.seatNumber = seatNumber;
        this.seatType   = seatType;
        this.seatPrice  = seatPrice;
    }

    public String getTicketID()   { return ticketID; }
    public User   getUser()       { return user; }
    public String getFlightId()   { return flightId; }
    public String getSeatNumber() { return seatNumber; }
    public String getSeatType()   { return seatType; }
    public String getSeatPrice()  { return seatPrice; }

    
    // ADD: ticket saves itself — better OOP than DB.saveTicket(ticket)
    public void save() throws SQLException { // // Add instead of save - save() not neccecary in Ticket
        db.update(
            "INSERT INTO tickets (ticket_id, user_id, flight_id, seat_number, seat_type, price)" +
            " VALUES ('"  + ticketID        + "', "
                         + user.getUserID() + ", '"
                         + flightId         + "', '"
                         + seatNumber       + "', '"
                         + seatType         + "', '"
                         + seatPrice        + "')"
        );
    }
    
    public boolean cancelTicket() throws SQLException {
        // FIX: original had a stray comma: "delete from tickets, where..."
        db.update("DELETE FROM tickets WHERE ticket_id = '" + ticketID + "'");
        return true;
    }

    public void printInvoice() {
        System.out.println("========== INVOICE ==========");
        System.out.println("Ticket ID   : " + ticketID);
        System.out.println("Passenger   : " + user.getF_name() + " " + user.getL_name());
        System.out.println("Flight      : " + flightId);
        System.out.println("Seat Number : " + seatNumber);
        System.out.println("Seat Type   : " + seatType);
        System.out.println("Price       : $" + seatPrice);
        System.out.println("=============================");
    }
}
