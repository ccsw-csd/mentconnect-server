package com.ccsw.mentconnect.role.dto;

import java.io.Serializable;

import com.ccsw.mentconnect.role.model.RoleTypeEnum;

public class RoleDto implements Serializable {

    private Long id;

    private String code;

    private RoleTypeEnum type;

    public RoleTypeEnum getType() {
        return type;
    }

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

    public void setType(RoleTypeEnum type) {
        this.type = type;
    }
}
