package com.crea.dev6.firstapi.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crea.dev6.firstapi.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

    private static List<Product> products = new ArrayList<Product>();
    
    static{
        products.add(new Product("Product 1", 1, 10.0, 100));
        products.add(new Product("Product 2", 2, 20.0, 200));
        products.add(new Product("Product 3", 3, 30.0, 300));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Product findByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public List<Product> save(Product product) {
        products.add(product);
        return products;
    }

    @Override
    public void update(Product product) {
        for (int i = 0 ; i < products.size() ; i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0 ; i < products.size() ; i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return;
            }
        }
    }


}
