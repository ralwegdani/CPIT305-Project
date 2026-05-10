
import java.io.Serializable;
import java.sql.SQLException;

public class User implements Serializable {

    private String F_name;
    private String L_name;
    private String email;
    private String passportNumber;
    private int    userID;
    private int    age;
    private static int count = 0;
    DB db = DB.getInstance();

    // FIX: age must be a constructor parameter, not assigned from itself
    public User(String F_name, String L_name, String email, String passportNumber, int age) {
        this.F_name         = F_name;
        this.L_name         = L_name;
        this.email          = email;
        this.passportNumber = passportNumber;
        this.age            = age;
        this.userID         = count++;
    }

    public boolean isAdult()   { return age >= 18; }
    public int     getAge()    { return age; }
    public int     getUserID() { return userID; }

    public String getF_name() { return F_name; }
    public void setF_name(String f_name) throws SQLException {
        F_name = f_name;
        // FIX: was updating L_name column instead of f_name
        db.update("UPDATE users SET f_name = '" + f_name + "' WHERE user_id = " + userID);
    }

    public String getL_name() { return L_name; }
    public void setL_name(String l_name) throws SQLException {
        L_name = l_name;
        db.update("UPDATE users SET l_name = '" + l_name + "' WHERE user_id = " + userID);
    }

    public String getEmail() { return email; }
    public void setEmail(String email) throws SQLException {
        this.email = email;
        db.update("UPDATE users SET email = '" + email + "' WHERE user_id = " + userID);
    }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) throws SQLException {
        this.passportNumber = passportNumber;
        db.update("UPDATE users SET passport_number = '" + passportNumber + "' WHERE user_id = " + userID);
    }
}