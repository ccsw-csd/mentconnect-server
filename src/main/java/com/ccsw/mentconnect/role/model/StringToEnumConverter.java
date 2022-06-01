package com.ccsw.mentconnect.role.model;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)

public class StringToEnumConverter implements AttributeConverter<RoleTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(RoleTypeEnum attribute) {

        if (attribute == null) {
            System.out.println("error convertadatabasecolumn");
            return null;
        }
        return attribute.getType();
    }

    public RoleTypeEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        System.out.println("error convertadatabasecolumn 2");

        return Stream.of(RoleTypeEnum.values()).filter(s ->

        s.getType().equalsIgnoreCase(dbData)).findFirst().orElseThrow(IllegalArgumentException::new);

    }
}