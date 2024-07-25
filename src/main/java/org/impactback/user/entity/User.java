package org.impactback.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.impactback.request.entity.Request;

import java.util.Collection;
/**
 * @author Isaac F. B. C.
 * @since 6/27/2024 - 10:15 PM
 */
@Entity
@Data
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role")
    private Object role;
    @Basic
    @Column(name = "state")
    private Byte state;
    @Getter
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Request> requestsById;

    public User() {}

    public User(int id, String name, String email, Object role, Byte state) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.state = state;
    }
}
