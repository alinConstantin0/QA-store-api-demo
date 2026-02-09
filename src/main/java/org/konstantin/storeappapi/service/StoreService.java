package org.konstantin.storeappapi.service;

import org.konstantin.storeappapi.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StoreService {

    private final List<Product> products = new ArrayList<>();

    public StoreService() {
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products); //returnam lista, dar read-only, mai safe
    }

    public Product findByName(String name) {
        if (name == null || name.isBlank()) {     // eliminam input invalid
            return null;
        }
        for (Product p : products) {      //verificam daca exista si il returnam
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;   // intrarea e valida dar nu exista produs
    }

    public void addProduct(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Product required");
        }
        products.add(p);
    }

    public Product createProduct(String name, double price, int stock) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name required");
        }
        if (price < 0 || stock < 0) {
            throw new IllegalArgumentException("Price/stock cannot be negative");
        }
        Product existing = findByName(name);
        if (existing != null) {
            throw new IllegalArgumentException("Duplicate");
        }
        Product input = new Product(name, price, stock);
        addProduct(input);
        return input;
    }

    public Boolean deleteProductByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name required");
        }
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                products.remove(p);
                return true;
            }
        }
        return false;
    }

    public Product updateStock(String name, int newStock) {
        {
            Product found = findByName(name);
            if (found == null || found.getName().isBlank()) {
                throw new IllegalArgumentException("Name required");
            }
            if (newStock < 0) {
                throw new IllegalArgumentException("Stock cannot be negative");
            }
            found.setStock(newStock);
            return found;
        }
    }
}

