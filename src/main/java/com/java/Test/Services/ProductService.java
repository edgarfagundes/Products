package com.java.Test.Services;

import com.java.Test.Entities.Product;
import com.java.Test.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //Metodo de busca por popularidade em ordem crescente
    public List<Product> findByScoreAsc(Pageable pageable){
         List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.ASC, "score"));
         return product;
    }

    //Metodo de busca por preço em ordem crescente
    public List<Product> findByPriceAsc(Pageable pageable){
        List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
        return product;
    }

    //Metodo de busca em ordem decrescente
    public List<Product> findByPriceDesc(Pageable pageable){
        List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        return product;
    }

    //Metodo de busca em ordem decrescente
    public List<Product> findByScoreDesc(Pageable pageable){
        List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
        return product;
    }

    //Metodo de busca em ordem alfabética
    public List<Product> findByAlphabet(Pageable pageable){
        List<Product> product = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return product;
    }

    //Metodo para salvar produto
    @Transactional
    public Optional<Product> save(Product product) {
        return Optional.of((Product) productRepository.save(product));
    }

    //Metodo de busca pelo ID
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    //Metodo de exclusão de produto por ID
    public Optional<Product> deleteById(long id) {
         productRepository.deleteById(id);
        return null;
    }

    public boolean existById(long id){
        return productRepository.existsById(id);
    }
}

