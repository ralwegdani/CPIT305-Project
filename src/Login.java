
import javax.swing.*;
import java.security.MessageDigest;

public class Login {
    DB db = DB.getInstance();
    String userName;
    String password;
    User user;
    public void login(String userName, String password) throws Exception {
        String query = "Select * from Login where username ="+userName;
        var result = db.retrive(query);
        if (result!=null){
            if(result.getString("password").equals(hashPassword(password))){
                //يدخل السيستم
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Wrong Password, try again please");
            }

        }
        else{
            signUp();
        }




    }
    public void signUp () throws Exception {
        userName = JOptionPane.showInputDialog("Enter username: ");
        password = JOptionPane.showInputDialog("Enter password: ");
        String email = JOptionPane.showInputDialog("Enter email: ");
        String Fname = JOptionPane.showInputDialog("Enter your first name: ");
        String Lname = JOptionPane.showInputDialog("Enter your Last name: ");
        String PassPort = JOptionPane.showInputDialog("Enter your passport number: ");

        user = new User(email,Fname,Lname,PassPort);
        String hashedPass = hashPassword(password);

        db.Add("INSERT INTO LOGIN VALUES("+user.getUserID()+","+userName+","+hashedPass+");");

    }


    public static String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());

        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
    public void logout(){

    }


}