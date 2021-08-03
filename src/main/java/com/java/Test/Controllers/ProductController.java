package com.java.Test.Controllers;

import com.java.Test.Entities.Product;
import com.java.Test.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController{

    @Autowired
    ProductService productService;

    //Listar todos os produtos
    @GetMapping
    public ResponseEntity<Page<Product>> listProducts(Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        if (!products.isEmpty()){
            return new ResponseEntity<Page<Product>>(products, HttpStatus.OK);

    }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //Listar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> listProduct(@PathVariable(value = "id")long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<Optional<Product>>(product, HttpStatus.OK);
        } else {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //Listar produto por ordem alfabética
    @GetMapping("/alphabet")
    public ResponseEntity<List<Product>> findByAlphabetAsc(Pageable pageable) {
        List<Product> productAlpha = (List<Product>) productService.findByAlphabet(pageable);
        if (!productAlpha.isEmpty()) {
            return new ResponseEntity<List<Product>>(productAlpha, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Listar produto por preço decrescente
    @GetMapping("/priceAsc")
    public ResponseEntity<List<Product>> findByPriceAsc(Pageable pageable) {
        List<Product> productPriceAsc = (List<Product>) productService.findByPriceAsc(pageable);
        if (!productPriceAsc.isEmpty()) {
            return new ResponseEntity<List<Product>>(productPriceAsc, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Listar produto por preço decrescente
    @GetMapping("/priceDesc")
    public ResponseEntity<List<Product>> findByPriceDesc(Pageable pageable) {
        List<Product> productPriceDesc = (List<Product>) productService.findByPriceDesc(pageable);
        if (!productPriceDesc.isEmpty()) {
            return new ResponseEntity<List<Product>>(productPriceDesc, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Listar produto por score (popularidade) em ordem crescente
    @GetMapping("/scoreAsc")
    public ResponseEntity<List<Product>> findByScoreAsc(Pageable pageable){
        List<Product> productAsc = (List<Product>) productService.findByScoreAsc(pageable);
       if (!productAsc.isEmpty()){
           return new ResponseEntity<List<Product>>(productAsc, HttpStatus.ACCEPTED);
       }else {
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       }

    }

    //Listar produto por score (popularidade) em ordem decrescente
    @GetMapping("/scoreDesc")
    public ResponseEntity<List<Product>> findByScoreDesc(Pageable pageable){
        List<Product> productDesc = (List<Product>) productService.findByScoreDesc(pageable);
        if (!productDesc.isEmpty()){
            return new ResponseEntity<List<Product>>(productDesc, HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    //Adicionar produtos
    @PostMapping
    public ResponseEntity<Optional<Product>> addProduct(@RequestBody Product product){
        Optional<Product> productAdd = productService.save(product);
        return new ResponseEntity<Optional<Product>>(productAdd, HttpStatus.CREATED);
    }

    //Alterar produtos
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Product>> updateProduct(@RequestBody Product product,@PathVariable(value = "id") long id ){
        Optional<Product> productId = productService.findById(id);
        Optional<Product> productUp = productService.save(product);
        if(!productId.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Optional<Product>>(productUp, HttpStatus.OK);
        }
    }


    //Deletar produtos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id")long id){
        Optional<Product> productId = productService.deleteById(id);
        if (productService.existById(id)){
            new ResponseEntity(productId, HttpStatus.NO_CONTENT);
        }else{
            new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return null;
    }
        }
