package ca.georgebrown.comp3074.t02;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String teaName;
    private int quantity;
    private float unitPrice;

    public Product(int id, String teaName, int quantity, float unitPrice) {
        this.id = id;
        this.teaName = teaName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product " + id;
    }
}
