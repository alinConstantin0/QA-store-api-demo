package org.konstantin.storeappapi.service;

import org.konstantin.storeappapi.model.Product;
import org.springframework.stereotype.Service;

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
        if (p == null) {                               // check sa nu fie invalid
            throw new IllegalArgumentException("Product added cannot be null");
        }
        Product existing = findByName(p.getName());              //verificare daca exista deja sa nu facem dupe
        if (existing != null) {                                   // daca existing contine un produs, adaugam stocul
            existing.addStock(p.getStock());                     // produsului trimis de user peste stocul lui existing
            return;
        }
        products.add(p);                                          // adaugam doar daca existing e null
    }

    public Product createProduct(String name, double price, int stock) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name required");
        }
        if (price < 0 || stock < 0) {
            throw new IllegalArgumentException("Price/Stock cannot be negative");
        }
        Product existing = findByName(name);
        if (existing != null) {
            throw new IllegalStateException("Duplicate");
        }
        Product input = new Product(name, price, stock);
        addProduct(input);
        return input;
    }

    public Boolean deleteProductByName(String name) {   //Boolean for demo, usually EntityResponse-HTTPS status
        if (name == null || name.isBlank()) {
            return false;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name))
                products.remove(i);
            return true;
        }
        return false;
    }

    public Product updateStock(String name, int newStock) {
        {
            Product found = findByName(name);
            if (found == null || found.getName().isBlank()) {
                throw new IllegalStateException("Name required");
            }
            if (newStock < 0) {
                throw new IllegalArgumentException("Stock cannot be lower than 0");
            }
            found.setStock(newStock);
            return found;
        }
    }
}

