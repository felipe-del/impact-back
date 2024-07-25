package org.impactback.product.repository;

import org.impactback.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Isaac F. B. C.
 * @since 7/1/2024 - 7:09 PM
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
