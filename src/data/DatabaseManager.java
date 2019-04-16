package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    private final String hostname = "jdbc:mysql://localhost/musiconclickdb";
    private final String username = "root";
    private final String password = "";
    private Connection connection = null;

    public DatabaseManager() {
        try{
            Connection connection = DriverManager.getConnection(this.hostname, this.username, this.password);
        }
        catch(Exception ex) {
            System.out.println("Database Connection Error");
            ex.printStackTrace();
        }
    }
}
