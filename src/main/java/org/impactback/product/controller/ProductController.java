package org.impactback.product.controller;

import org.impactback.product.entity.Product;
import org.impactback.product.service.implement.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Isaac F. B. C.
 * @since 7/1/2024 - 7:07 PM
 */

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){return ResponseEntity.ok(productService.findAll());}

}
