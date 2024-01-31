package com.demo.springbootrediscrudwithkeyvalue.service;

import com.demo.springbootrediscrudwithkeyvalue.entity.Product;
import com.demo.springbootrediscrudwithkeyvalue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;


    public Product save(Product product) {
        return repo.save(product);
    }


    public List<Product> getAllProducts() {
        return repo.findAll();
    }


    public Product findProduct(int id) {
        return repo.findProductById(id);
    }

    public String remove(int id)   {
        return repo.deleteProduct(id);
    }



}
