package com.java.Test.Services;

import com.java.Test.Entities.Product;
import com.java.Test.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public List<Product> findByScoreAsc(Pageable pageable){
         List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.ASC, "score"));
         return product;
    }

    public List<Product> findByScoreDesc(Pageable pageable){
        List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
        return product;
    }

    public Optional<Product> save(Product product) {
        return Optional.of((Product) productRepository.save(product));
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public Optional<Void> deleteById(long id) {
        productRepository.deleteById(id);
         return null;
    }
}

