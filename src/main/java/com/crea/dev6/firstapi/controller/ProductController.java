package com.crea.dev6.firstapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crea.dev6.firstapi.dao.ProductDao;
import com.crea.dev6.firstapi.model.Product;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    //renvoie la liste des tous les produits
    @GetMapping("/products")
    public List<Product> findAll() {
        return productDao.findAll();
    }

    //renvoie un produit inexistant
    @GetMapping("/productfake")
    public Product findFake() {
        return new Product("Fake Product", 1, 0, 0);
    }

    //renvoie un produit par son id
    @GetMapping("/product/{id}")
    public Product findById(@PathVariable("id") int id) {
        return productDao.findById(id);
    }

    //ajout d'un produit
    @PostMapping("/addProduct")
    public ResponseEntity<Void> addProduit(@RequestBody Product product) {
        List<Product> productAdded = productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(productAdded.get(0).getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }
   

}
