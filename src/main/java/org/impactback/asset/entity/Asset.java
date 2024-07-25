package org.impactback.asset.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.impactback.assetRequest.entity.AssetRequest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:15 PM
 */
@Entity
@Data
public class Asset {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "purchasing_manager")
    private String purchasingManager;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Basic
    @Column(name = "value")
    private BigDecimal value;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "responsible")
    private String responsible;
    @Basic
    @Column(name = "supplier")
    private String supplier;
    @Basic
    @Column(name = "warranty")
    private String warranty;
    @Basic
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "assetByAssetId")
    private Collection<AssetRequest> assetRequestsById;

}
