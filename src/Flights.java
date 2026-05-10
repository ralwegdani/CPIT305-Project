
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Flights {
    protected String flightNo;
    private   String departure;
    private   String destination;
    private   String departureTime;
    private   String arrivalTime;
    private   Date   date;
    protected DB     db = DB.getInstance();
    static ArrayList<Flights> flights = new ArrayList<>();

    public Flights() {}

    public void setFlights() throws SQLException {
        try (ResultSet rs = db.retrive("SELECT * FROM flights WHERE flight_id = '" + flightNo + "'")) {
            while (rs.next()) {
                this.flightNo      = rs.getString("flight_id");
                departure          = rs.getString("departure_city");
                destination        = rs.getString("destination");
                departureTime      = rs.getString("departure_time");
                arrivalTime        = rs.getString("arrival_time");
                date               = rs.getDate("date");
                flights.add(this);
            }
        }
    }

    public void display() {}

    // FIX: original tried to parse a ResultSet object as an integer — always threw an exception
    public int getTotalSeats() throws SQLException {
        try (ResultSet rs = db.retrive(
                "SELECT total_b_seats + total_e_seats AS total FROM flights WHERE flight_id = '" + flightNo + "'")) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
}