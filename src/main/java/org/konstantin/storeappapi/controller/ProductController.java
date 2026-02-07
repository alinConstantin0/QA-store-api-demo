package org.konstantin.storeappapi.controller;

import org.konstantin.storeappapi.dto.ProductCreateRequest;
import org.konstantin.storeappapi.dto.UpdateStockRequest;
import org.konstantin.storeappapi.model.Product;
import org.konstantin.storeappapi.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class ProductController {
    private final StoreService storeService;

    public ProductController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/products")                //get all prod
    public List<Product> disProducts() {
        return storeService.getAllProducts();
    }

    @PostMapping("/products")
    // post products, apelam logica, facem produs fara checkuri, checkurile sunt in metoda deja
    public Product postProd(@RequestBody ProductCreateRequest req) {
        return storeService.createProduct(req.getName(), req.getPrice(), req.getStock());
    }

    @GetMapping("/products/{name}")                 // get product by name
    public Product getProdByName(@PathVariable String name) {     //path variable trimite name din URL in variabila metodei
        Product found = storeService.findByName(name);
        if (found == null || found.getName().isBlank()) {
            throw new ResponseStatusException(NOT_FOUND,"Not found");
        }
        return found;
    }

    @DeleteMapping("/products/{name}")
    public String deleteByName(@PathVariable String name) {
        if (storeService.deleteProductByName(name)) {
            return "Deleted";
        } else {
            return "Not found";
        }
    }

    @PatchMapping("/products/{name}/req")
    public Product patchProdStock(@PathVariable String name, @RequestBody UpdateStockRequest req) {
        if (name == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        storeService.findByName(name).setStock(req.getStock());
        return storeService.findByName(name);
    }
}
