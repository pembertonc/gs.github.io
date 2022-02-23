package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Cedric Pemberton
 */
@Entity
public class Contact extends BaseEntity {

    @Basic
    @Email(message = "Not a valid Email ")
    private String email;
    @Basic(optional = false)
    @Column(nullable = false, length = 14)
    @NotBlank(message = "Telehone 1 is required")
    private String telephone1;
    @Basic
    @Column(length = 14)
    private String telephone2;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact email(String email) {
        this.email = email;
        return this;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public Contact telephone1(String telephone1) {
        this.telephone1 = telephone1;
        return this;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public Contact telephone2(String telephone2) {
        this.telephone2 = telephone2;
        return this;
    }

}