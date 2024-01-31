package com.demo.springbootrediscrudwithkeyvalue.repository;

import com.demo.springbootrediscrudwithkeyvalue.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    public static final String HASH_KEY = "Product";
    @Autowired
    private RedisTemplate<String,Object> template;

    public Product save(Product product){
        template.opsForHash().put(HASH_KEY,product.getId(),product);
        return product;
    }

    public List<Product> findAll(){
        HashOperations<String, Object, Product> hashOperations = template.opsForHash();
        return hashOperations.values(HASH_KEY);
    }

    public Product findProductById(int id){
        System.out.println("called findProductById from database");
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }
}
