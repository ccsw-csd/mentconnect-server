package com.ccsw.mentconnect.security.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Class to store user information which is later encapsulated into {@link Authentication} object.
 *
 */
public class UserDetailsJWTDto implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private List<String> roles = new ArrayList<>();

    private Collection<? extends GrantedAuthority> authorities;

    private String name;

    private String surnames;

    private String email;

    /**
     * @return id
     */
    public Long getId() {
      return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
      this.id = id;
    }

    /**
     * @return username
     */
    @Override
    public String getUsername() {

      return this.username;
    }

    /**
     * @param username new value of {@link #getUsername}.
     */
    public void setUsername(String username) {

      this.username = username;
    }

    /**
     * @return authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

      if (this.authorities == null)
        this.authorities = convertPermissionsToAuthorities();

      return this.authorities;
    }

    private Collection<GrantedAuthority> convertPermissionsToAuthorities() {

      Collection<GrantedAuthority> userAuthorities = new ArrayList<>();

      for (String role : this.roles) {
        userAuthorities.add(new GrantedAuthority() {
          private static final long serialVersionUID = 1L;

          @Override
          public String getAuthority() {

            return role;
          }
        });
      }

      return userAuthorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {

      return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {

      return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {

      return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {

      return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {

      return true;
    }

    /**
     * @return the email
     */
    public String getEmail() {

      return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {

      this.email = email;
    }

    /**
     * @return name
     */
    public String getName() {

      return this.name;
    }

    /**
     * @param name new value of {@link #getName}.
     */
    public void setName(String name) {

      this.name = name;
    }

    /**
     * @return surnames
     */
    public String getSurnames() {

      return this.surnames;
    }

    /**
     * @param surnames new value of {@link #getSurnames}.
     */
    public void setSurnames(String surnames) {

      this.surnames = surnames;
    }

    /**
     * @return roles
     */
    public List<String> getRoles() {

      return this.roles;
    }

    /**
     * @param roles new value of {@link #getRoles}.
     */
    public void setRoles(List<String> roles) {

      this.roles = roles;
    }

    /**
     * @param role new value of {@link #getRoles}.
     */
    public void addRole(String role) {

      this.roles.add(role);
    }
}
