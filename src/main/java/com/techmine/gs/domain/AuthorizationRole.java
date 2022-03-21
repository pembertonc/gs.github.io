package com.techmine.gs.domain;

import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * @author Cedric-Pemberton
 */
@Entity
public class AuthorizationRole extends BaseEntity {

    @Basic
    private String roleName = "";
    @Basic
    private String description = "";

    public Optional<String> getRoleName() {
        return Optional.ofNullable(roleName);
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public AuthorizationRole roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorizationRole description(String description) {
        this.description = description;
        return this;
    }

}