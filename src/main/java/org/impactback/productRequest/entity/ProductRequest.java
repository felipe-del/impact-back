package org.impactback.productRequest.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.impactback.request.entity.Request;
import org.impactback.product.entity.Product;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:15 PM
 */
@Entity
@Table(name = "product_request", schema = "inventorybd")
@Data
public class ProductRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false)
    private Request requestByRequestId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product productByProductId;
}
