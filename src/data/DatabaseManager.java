package data;

import java.sql.*;

public class DatabaseManager {
    private static DatabaseManager instance = null;
    private final String hostname = "jdbc:mysql://localhost/musiconclickdb";
    private final String username = "root";
    private final String password = "";
    private Connection connection = null;

    private DatabaseManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.hostname, this.username, this.password);
        } catch (Exception ex) {
            System.out.println("Database Connection Error");
            ex.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public boolean registerUser(String username, String email, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO register(username,email,password) VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isUserValid(String Username,String Password) {


        String sql="SELECT  * FROM register WHERE username=? and password=?";
        //String query = ;  //get username   //
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,Username);
            stmt.setString(2,Password);
            ResultSet rs = stmt.executeQuery();


            if(!rs.next()) {
               return false;
            }else {
                return true ;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return true ;
        }
    }

}
