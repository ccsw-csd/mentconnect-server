package com.ccsw.mentconnect.user.dto;

import com.ccsw.mentconnect.role.dto.RoleDto;

import java.util.List;

/**
 * @author amirzoya
 *
 *         Clase DTO de la entidad UserEntity
 *
 */
public class UserFullDto {

    private Long id;

    private String username;

    private String name;

    private String surnames;

    private String email;

    private List<RoleDto> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
