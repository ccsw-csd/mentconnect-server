package com.ccsw.mentconnect.user.dto;

import java.util.List;

import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.role.dto.RoleDto;

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

    private List<PatientDto> patients;

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

    public List<PatientDto> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDto> patients) {
        this.patients = patients;
    }

}
