package org.impactback.request.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.impactback.assetRequest.entity.AssetRequest;
import org.impactback.spaceRequest.entity.SpaceRequest;
import org.impactback.user.entity.User;
import org.impactback.productRequest.entity.ProductRequest;

import java.sql.Date;
import java.util.Collection;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:15 PM
 */
@Entity
@Data
public class Request {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "requestByRequestId")
    private Collection<AssetRequest> assetRequestsById;
    @OneToMany(mappedBy = "requestByRequestId")
    private Collection<ProductRequest> productRequestsById;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userByUserId;
    @OneToMany(mappedBy = "requestByRequestId")
    private Collection<SpaceRequest> spaceRequestsById;

}
