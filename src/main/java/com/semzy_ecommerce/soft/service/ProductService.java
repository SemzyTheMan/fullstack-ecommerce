package com.semzy_ecommerce.soft.service;

import com.semzy_ecommerce.soft.dao.ProductRepository;
import com.semzy_ecommerce.soft.dtos.BoxItem;
import com.semzy_ecommerce.soft.dtos.requests.ProductRequest;
import com.semzy_ecommerce.soft.entity.InsideBox;
import com.semzy_ecommerce.soft.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addProduct(ProductRequest productRequest) {

        Product newProduct = new Product();
        List<InsideBox> allBoxItems = new ArrayList<>();
        for(BoxItem box:productRequest.getInThebox()){
            allBoxItems.add(new InsideBox(box.getQuantity(), box.getItem()));
        }

        newProduct.setName(productRequest.getName());
        newProduct.setDescription(productRequest.getDescription());
        newProduct.setInThebox(allBoxItems);
        newProduct.setFeatures(productRequest.getFeatures());
        newProduct.setPrice(productRequest.getPrice());
        newProduct.setQuantityAvailable(productRequest.getQuantityAvailable());
        newProduct.setMainImageUrl(cloudinaryService.
                uploadFile(productRequest.getMainImageUrl(), "product_image"));
        newProduct.setImageOne(cloudinaryService.
                uploadFile(productRequest.getImageOne(), "product_image"));
        newProduct.setImageTwo(cloudinaryService.
                uploadFile(productRequest.getImageTwo(), "product_image"));
        newProduct.setImageThree(cloudinaryService.
                uploadFile(productRequest.getImageThree(), "product_image"));


        return productRepository.save(newProduct);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
