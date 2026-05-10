import java.sql.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
 class DB {
    private String url = "jdbc:postgresql://db.rqmohzkdbsvuazdmzjkj.supabase.co:5432/postgres";
    private String user = "postgres";
    private String password = "305ProjectAirline";
    private static Statement stmt;
    private static Connection conn;
    private static ResultSet rs;
    private static boolean flag = false ;
    private static DB db;



    private DB() {
     init();
    }

    private void init() {


            try {
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement();
                flag = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static DB getInstance() {
        if(!flag) {
           DB db = new DB();
        }
        return db;
        }



    public static void Add (String query ) {
        try {

            stmt.executeUpdate(query);
        }catch(Exception e) {
            e.fillInStackTrace();
        }

    }
    public static ResultSet retrive (String query) {
        try {
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            e.fillInStackTrace();

        }
        return rs;
    }
        public static void update (String update){
            try {

                stmt.executeUpdate(update);
            } catch (Exception e) {
                e.fillInStackTrace();
            }

        }
        public static void close () {
            try {

                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }


}
