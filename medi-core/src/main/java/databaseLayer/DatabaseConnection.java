package databaseLayer;

import java.sql.*;

public class DatabaseConnection {
    private final String URL = "jdbc:mysql://localhost:3306/medicare";
    private final String username = "root";
    private final String password = "";

    private static DatabaseConnection instance;

    private Connection connection;

    public ResultSet resultSet;

    public PreparedStatement preparedStatement;

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

    public void setPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet ExecutePreparedStatement()
    {
        try
        {
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Result not found");
                return null;
            } else {
                return resultSet;
            }
        }catch (SQLException ex)
        {
            System.out.println("SQL Error "+ex.getMessage());
            return resultSet;
        }
    }
}
