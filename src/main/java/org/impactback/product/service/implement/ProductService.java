package org.impactback.product.service.implement;

import org.impactback.product.entity.Product;
import org.impactback.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Isaac F. B. C.
 * @since 7/1/2024 - 7:08 PM
 */
@Service("productService")
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
