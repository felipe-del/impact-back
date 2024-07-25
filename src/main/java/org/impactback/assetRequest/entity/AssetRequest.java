package org.impactback.assetRequest.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.impactback.asset.entity.Asset;
import org.impactback.request.entity.Request;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:15 PM
 */
@Entity
@Table(name = "asset_request", schema = "inventorybd")
@Data
public class AssetRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false)
    private Request requestByRequestId;
    @ManyToOne
    @JoinColumn(name = "asset_id", referencedColumnName = "id", nullable = false)
    private Asset assetByAssetId;
}
