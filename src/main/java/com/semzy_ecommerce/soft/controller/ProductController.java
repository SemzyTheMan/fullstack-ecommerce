package com.semzy_ecommerce.soft.controller;


import com.semzy_ecommerce.soft.dao.ProductRepository;
import com.semzy_ecommerce.soft.dtos.ProductboxDTO;
import com.semzy_ecommerce.soft.dtos.requests.ProductRequest;
import com.semzy_ecommerce.soft.entity.Product;
import com.semzy_ecommerce.soft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        return  new ResponseEntity<>(allProducts,HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId){
        Product tempProduct = productRepository.findById(productId);
        return  new ResponseEntity<>(tempProduct,HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductRequest productRequest) {
        Product saved = productService.addProduct(productRequest);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/addProductBoxItems")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> addProductBoxItem(@RequestBody ProductboxDTO productboxDTO) {
        Product tempproduct = productService.getProductById(productboxDTO.getId());
        tempproduct.addProductBoxContent(productboxDTO.getItems());
        Product returned = productRepository.save(tempproduct);
        return new ResponseEntity<>(returned, HttpStatus.CREATED);
    }




}
