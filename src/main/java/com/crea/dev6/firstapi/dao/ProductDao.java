package com.crea.dev6.firstapi.dao;

import java.util.List;

import com.crea.dev6.firstapi.model.Product;

/**
 * This interface represents a data access object for managing products.
 */
public interface ProductDao {

    /**
     * Retrieves all products from the data source.
     *
     * @return a list of all products
     */
    public List<Product> findAll();
    
    /**
     * Retrieves a product by its ID from the data source.
     *
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID, or null if not found
     */
    public Product findById(int id);
    
    /**
     * Retrieves a product by its name from the data source.
     *
     * @param name the name of the product to retrieve
     * @return the product with the specified name, or null if not found
     */
    public Product findByName(String name);
    
    /**
     * Saves a new product to the data source.
     *
     * @param product the product to save
     */
    public List<Product> save(Product product);
    
    /**
     * Updates an existing product in the data source.
     *
     * @param product the product to update
     */
    public void update(Product product);
    
    /**
     * Deletes a product from the data source by its ID.
     *
     * @param id the ID of the product to delete
     */
    public void delete(int id);

}
