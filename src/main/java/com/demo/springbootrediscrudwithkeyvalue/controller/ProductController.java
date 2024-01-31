package com.demo.springbootrediscrudwithkeyvalue.controller;

import com.demo.springbootrediscrudwithkeyvalue.entity.Product;
import com.demo.springbootrediscrudwithkeyvalue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CacheConfig(cacheNames={"Product"})
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private CacheManager cacheManager;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    @Cacheable(key="#id",value = "Product",unless = "#result.price>1000")
    public Product findProductById(@PathVariable int id) {
        return service.findProduct(id);
    }
    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id",value = "Product")
    public String remove(@PathVariable int id)   {
        return service.remove(id);
    }

        @DeleteMapping(path = "/clearCache")
    public ResponseEntity<String> clearCache(){

        if(cacheManager.getCacheNames().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cache record has been found");
        }else {


            for(String name:cacheManager.getCacheNames()){
                /* printing cache name */
                System.out.println(name);

                /* clear cache by name */
                cacheManager.getCache(name).clear();

            }
            return ResponseEntity.status(HttpStatus.OK).body("All cache has been cleared");
        }
    }
}
