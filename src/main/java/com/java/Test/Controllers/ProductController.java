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

    @GetMapping
    public ResponseEntity<Page<Product>> listProducts(Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        if (products.isEmpty()){
            return new ResponseEntity<Page<Product>>(products, HttpStatus.OK);
    }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> listProduct(@PathVariable(value = "id")long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<Optional<Product>>(product, HttpStatus.OK);
        } else {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/scoreAsc")
    public ResponseEntity<List<Product>> findByScoreAsc(Pageable pageable){
        List<Product> productAsc = (List<Product>) productService.findByScoreAsc(pageable);
       if (!productAsc.isEmpty()){
           return new ResponseEntity<List<Product>>(productAsc, HttpStatus.ACCEPTED);
       }else {
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       }

    }
    @GetMapping("/scoreDesc")
    public ResponseEntity<List<Product>> findByScoreDesc(Pageable pageable){
        List<Product> productDesc = (List<Product>) productService.findByScoreDesc(pageable);
        if (!productDesc.isEmpty()){
            return new ResponseEntity<List<Product>>(productDesc, HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    public ResponseEntity<Optional<Product>> addProduct(@RequestBody Product product){
        Optional<Product> productAdd = productService.save(product);
        return new ResponseEntity<Optional<Product>>(productAdd, HttpStatus.CREATED);
    }

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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id")long id){
        Optional<Void> productId = productService.deleteById(id);
        if (productId.isPresent()){
             new ResponseEntity<Optional<Void>>(productId, HttpStatus.NO_CONTENT);
        }else{
           return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return null;
    }

}
