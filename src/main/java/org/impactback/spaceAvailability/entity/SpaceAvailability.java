package org.impactback.spaceAvailability.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.impactback.space.entity.Space;

import java.sql.Date;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:15 PM
 */
@Entity
@Table(name = "space_availability", schema = "inventorybd")
@Data
public class SpaceAvailability {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "start_availability")
    private Date startAvailability;
    @Basic
    @Column(name = "end_availability")
    private Date endAvailability;
    @ManyToOne
    @JoinColumn(name = "space_id", referencedColumnName = "id")
    private Space spaceBySpaceId;

}
