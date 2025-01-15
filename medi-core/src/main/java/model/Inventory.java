package model;

public class Inventory {
    private int id;
    private String medicine_name;
    private int quantity;
    private int threshold;
    private double price;

    public Inventory(int id, String medicine_name, int quantity, int threshold ,double price) {
        this.id = id;
        this.medicine_name = medicine_name;
        this.quantity = quantity;
        this.threshold = threshold;
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

    public int getThreshold() {
        return threshold;
    }

    public double getPrice() {
        return price;
    }
}
