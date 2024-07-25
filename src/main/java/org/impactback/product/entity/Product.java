package org.impactback.product.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.impactback.productRequest.entity.ProductRequest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
@Data
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "purchase_method")
    private String purchaseMethod;
    @Basic
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Basic
    @Column(name = "expiry_date")
    private Date expiryDate;
    @Basic
    @Column(name = "value")
    private BigDecimal value;
    @Basic
    @Column(name = "supplier")
    private String supplier;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "unit_measure")
    private String unitMeasure;
    @OneToMany(mappedBy = "productByProductId")
    private Collection<ProductRequest> productRequestsById;

}
