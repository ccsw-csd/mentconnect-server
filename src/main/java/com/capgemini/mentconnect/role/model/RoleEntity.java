package com.capgemini.mentconnect.role.model;

import javax.persistence.*;

/**
 * @author amirzoya
 *
 *         Entidad que representa los datos almacenados en la tabla role
 *
 */
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RoleTypeEnum type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RoleTypeEnum getType() {
        return type;
    }

    public void setType(RoleTypeEnum type) {
        this.type = type;
    }



}
