package controller;

import model.Inventory;
import serviceLayer.InventoryService;

import java.sql.ResultSet;

public class InventoryController {
    Inventory ObjInventory;
    InventoryService ObjInventoryService;

    public Inventory addInventoryToDB(int id, String medicine_name, int quantity, int threshold ,double price) {
        ObjInventory = new Inventory(id, medicine_name, quantity,threshold ,price);
        return ObjInventory;
    }

    public boolean addInventoryToDB() {
        ObjInventoryService = new InventoryService();
        return ObjInventoryService.addInventory(ObjInventory);
    }

    public ResultSet getInventoryDetails() {
        ObjInventoryService = new InventoryService();
        return ObjInventoryService.getInventoryDetails();
    }


}
