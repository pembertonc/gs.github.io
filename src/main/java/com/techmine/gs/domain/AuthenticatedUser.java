package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Cedric Pemberton
 */
@Entity
public class AuthenticatedUser extends BaseEntity {

    @Basic(optional = false)
    @Column(unique = true, nullable = false, length = 64)
    @NotBlank(message = "User Name is required")
    private String userName;
    @Basic(optional = false)
    @Column(nullable = false, length = 12)
    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 12, message = "Password must be from 4 to 12 characters long.")
    private String password;
    @Basic
    private String role;
    @OneToOne
    private Person person;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public AuthenticatedUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticatedUser password(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AuthenticatedUser role(String role) {
        this.role = role;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public AuthenticatedUser person(Person person) {
        this.person = person;
        return this;
    }

}