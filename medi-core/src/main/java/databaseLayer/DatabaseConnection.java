package databaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private final String URL = "jdbc:mysql://localhost:3306/medicare";
    private final String username = "root";
    private final String password = "";
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found" + e.getMessage());
        }
        catch (SQLException e) {
            System.out.println("Connection failed" + e.getMessage());
        }
    }

    // Singleton pattern
    public static DatabaseConnection getSingleInstance() {
        try{
            if (instance == null) {
                instance = new DatabaseConnection();
            }
            else if (instance.connection.isClosed()) {
                instance = new DatabaseConnection();
            }
            else{
                return instance;
            }
            return instance;
        }
        catch(SQLException e){
            System.out.println("Connection failed" + e.getMessage());
            return null;
        }
    }

    public boolean ExecuteSQL(String sqlQuery) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlQuery);
            return result > 0;
        } catch (SQLException e) {
            System.out.println("SQL execution failed" + e.getMessage());
            return false;
        }
    }
}
