package com.ccsw.mentconnect.role.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

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
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)

    @Column(name = "type")
    @Convert(converter = StringToEnumConverter.class)
    @JsonValue
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
