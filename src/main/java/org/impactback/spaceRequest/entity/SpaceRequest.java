package org.impactback.spaceRequest.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.impactback.request.entity.Request;
import org.impactback.space.entity.Space;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:15 PM
 */
@Entity
@Table(name = "space_request", schema = "inventorybd")
@Data
public class SpaceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false)
    private Request requestByRequestId;
    @ManyToOne
    @JoinColumn(name = "space_id", referencedColumnName = "id", nullable = false)
    private Space spaceBySpaceId;

}
