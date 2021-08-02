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

    //Metodo de busca
    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    //Metodo de busca em ordem crescente
    public List<Product> findByScoreAsc(Pageable pageable){
         List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.ASC, "score"));
         return product;
    }

    //Metodo de busca em ordem decrescente
    public List<Product> findByScoreDesc(Pageable pageable){
        List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
        return product;
    }

    //Metodo para salvar produto
    public Optional<Product> save(Product product) {
        return Optional.of((Product) productRepository.save(product));
    }

    //Metodo de busca pelo ID
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    //Metodo de exclusão de produto por ID
    public Optional<Void> deleteById(long id) {
        productRepository.deleteById(id);
         return null;
    }
}

