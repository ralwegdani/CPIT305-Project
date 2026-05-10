
import java.sql.*;

class DB<T> {
    private String url      = "jdbc:postgresql://db.rqmohzkdbsvuazdmzjkj.supabase.co:5432/postgres";
    private String user     = "postgres";
    private String password = "305ProjectAirline";
    private static Statement  stmt;
    private static Connection conn;
    private static ResultSet  rs;
    private static DB         db;

    public DB() { init(); }

    private void init() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            // NOTE: do NOT call conn.close() here — stmt becomes unusable if you do
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DB getInstance() {
        if (db == null) {          // FIX: was checking a flag but never assigning db
            db = new DB();
        }
        return db;
    }

    // FIX: was missing VALUES keyword and had wrong SQL structure
    public static void Add(Object o) throws SQLException {
        User u = (User) o;
        String query = "INSERT INTO users (email, f_name, l_name, passport_number) VALUES ('"
                + u.getEmail()          + "', '"
                + u.getF_name()         + "', '"
                + u.getL_name()         + "', '"
                + u.getPassportNumber() + "')";
        stmt.executeUpdate(query);
    }

    // Saves a completed booking ticket to the DB
    public static void saveTicket(Ticket t) throws SQLException {
        String query = "INSERT INTO tickets (ticket_id, user_id, flight_id, seat_number, seat_type, price) VALUES ('"
                + t.getTicketID()        + "', "
                + t.getUser().getUserID() + ", '"
                + t.getFlightId()        + "', '"
                + t.getSeatNumber()      + "', '"
                + t.getSeatType()        + "', '"
                + t.getSeatPrice()       + "')";
        stmt.executeUpdate(query);
    }

    public static ResultSet retrive(String query) throws SQLException {
        rs = stmt.executeQuery(query);
        return rs;
    }

    public static void update(String update) throws SQLException {
        stmt.executeUpdate(update);
    }
}