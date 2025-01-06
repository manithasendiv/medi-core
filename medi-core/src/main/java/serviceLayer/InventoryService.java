package serviceLayer;

import databaseLayer.DatabaseConnection;
import model.Inventory;

import java.sql.ResultSet;

public class InventoryService {
    private DatabaseConnection singleConnection;

    public InventoryService() {
        singleConnection = DatabaseConnection.getSingleInstance();
    }

    public boolean addInventory(Inventory inventory) {
        try {
            String query = "INSERT INTO pharmacy_inventory(medicine_name, quantity, price) VALUES('"+inventory.getMedicine_name()+"','" +inventory.getQuantity()+ "','"+inventory.getPrice()+"')";
            boolean result = singleConnection.ExecuteSQL(query);
            return result;
        } catch (Exception e) {
            System.out.println("Error in adding inventory" + e.getMessage());
            return false;
        }
    }

    public ResultSet getInventoryDetails() {
        try {
            singleConnection.setPreparedStatement("SELECT * FROM pharmacy_inventory");
            ResultSet resultSet = singleConnection.ExecutePreparedStatement();
            return resultSet;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}