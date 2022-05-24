package com.ccsw.mentconnect.role.dto;

import com.ccsw.mentconnect.role.model.RoleTypeEnum;

public class RoleDto {

    private Long id;

    private String code;

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
