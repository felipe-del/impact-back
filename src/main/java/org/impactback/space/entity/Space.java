package org.impactback.space.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.impactback.spaceAvailability.entity.SpaceAvailability;
import org.impactback.spaceRequest.entity.SpaceRequest;

import java.util.Collection;

/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:15 PM
 */
@Entity
@Data
public class Space {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "space_number")
    private Integer spaceNumber;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "max_people")
    private Integer maxPeople;
    @Basic
    @Column(name = "equipment_availability")
    private String equipmentAvailability;
    @OneToMany(mappedBy = "spaceBySpaceId")
    private Collection<SpaceAvailability> spaceAvailabilitiesById;
    @OneToMany(mappedBy = "spaceBySpaceId")
    private Collection<SpaceRequest> spaceRequestsById;
}
