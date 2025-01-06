package model;

public class Inventory {
    private int id;
    private String medicine_name;
    private int quantity;
    private double price;

    public Inventory(int id, String medicine_name, int quantity, double price) {
        this.id = id;
        this.medicine_name = medicine_name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
