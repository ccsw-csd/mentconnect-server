package com.ccsw.mentconnect.userrole.model;

import com.ccsw.mentconnect.role.model.RoleEntity;
import com.ccsw.mentconnect.user.model.UserEntity;

import javax.persistence.*;

/**
 * @author amirzoya
 *
 *         Entidad que representa los datos almacenados en la tabla user_role
 *
 */
@Entity
@Table(name = "user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
