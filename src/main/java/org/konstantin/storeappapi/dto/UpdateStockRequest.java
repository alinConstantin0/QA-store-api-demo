package org.konstantin.storeappapi.dto;


public class UpdateStockRequest {
    int stock;

    public UpdateStockRequest() {

    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
}
