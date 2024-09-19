package com.crea.dev6.firstapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crea.dev6.firstapi.model.Product;

/**
 * This interface represents a data access object for managing products.
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

public Product findByName(String name);

public List<Product> findByPriceGreaterThan(double price);

    
}
