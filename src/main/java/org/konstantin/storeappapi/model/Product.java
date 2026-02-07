package org.konstantin.storeappapi.model;

public class Product {
    private final String name;
    private final double price;
    private int stock;

    public Product(String name, double price, int stock) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        if (price < 0) throw new IllegalArgumentException("Price cannot be lower than 0");
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be lower than 0");

        this.name = name;
        this.price = price;
        this.stock = stock;
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


    public void addStock(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Stock cannot be lower than 0");
        stock += amount;
    }

    public void setStock(int newStock) {
        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot be lower than 0");
        }

        stock = newStock;
    }

    public void delStock(int amount) {
        if (amount > stock) throw new IllegalArgumentException("Cannot remove more stock than there actually is");
        if (amount < 0) throw new IllegalArgumentException("Amount removed cannot be lower than 0");
        stock -= amount;
    }

    @Override
    public String toString() {
        return ("Product = " + this.name + ", Price = " + this.price + ", Stock = " + this.stock);
    }
}
