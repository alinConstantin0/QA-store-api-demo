package org.konstantin.storeappapi.dto;

public class ProductCreateRequest {

    private String name;
    private double price;
    private int stock;

    public ProductCreateRequest() {

    }

    public void setName(String input) {
        this.name = input;
    }

    public void setPrice(double input) {
        this.price = input;
    }

    public void setStock(int input) {
        this.stock = input;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
