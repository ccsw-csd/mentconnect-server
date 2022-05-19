package com.ccsw.mentconnect.user.dto;

import com.ccsw.mentconnect.common.pagination.PagingAndSorting;

/**
 * @author amirzoya
 *
 *         Clase DTO de busqueda paginada de la entidad UserEntity
 *
 */
public class UserSearchDto extends PagingAndSorting {

    private Long id;

    private String username;

    private String name;

    private String surnames;

    private String email;

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

}
