
import java.sql.ResultSet;
import java.sql.SQLException;

public class Economey extends Flights {
    private int     total_seats;
    private String  seatNumber;
    private boolean availability;
    private double  price;

    public Economey(String flightNo) throws SQLException {
        super();
        super.flightNo = flightNo;  // FIX: must set flightNo before getTotalSeats() uses it
        try (ResultSet rs = db.retrive("SELECT * FROM economy WHERE flight_id = '" + flightNo + "'")) {
            if (rs.next()) {
                seatNumber   = rs.getString("seat_number");
                availability = rs.getBoolean("availability");
                price        = rs.getDouble("price");
            }
        }
        total_seats = super.getTotalSeats();
    }

    public double getPrice() { return price; }  // FIX: was missing — needed by BookingManager

    @Override
    public void display() {}

    // FIX: SQL was malformed — missing '=' and had wrong comma before WHERE
    public void SetSeatNumber(String operation) throws SQLException {
        if (operation.equalsIgnoreCase("CANCEL")) {
            db.update("UPDATE flights SET total_e_seats = total_e_seats + 1 WHERE flight_id = '" + super.flightNo + "'");
            total_seats++;
        } else {
            db.update("UPDATE flights SET total_e_seats = total_e_seats - 1 WHERE flight_id = '" + super.flightNo + "'");
            total_seats--;
        }
    }
}
