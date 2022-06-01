package com.ccsw.mentconnect.role.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RoleTypeEnum {

    INT("INT"), EXT("EXT");

    public String type;

    RoleTypeEnum(String type) {
        this.type = type;

    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonCreator
    public static RoleTypeEnum fromJson(@JsonProperty("type") String type) {
        return valueOf(type);

    }

}
