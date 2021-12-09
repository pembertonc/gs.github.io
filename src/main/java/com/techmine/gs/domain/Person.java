package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Cedric Pemberton
 */
@Entity
public class Person extends BaseEntity {

    @Basic(optional = false)
    @Column(nullable = false, length = 64)
    @NotEmpty(message = "FirstName is required")
    @Size(min = 1, max = 64, message = "First Name must not be longer than  64 characters")
    private String firstName;
    @Basic
    @Size(min = 0, max = 64, message = "Other Name can not be longer than 64 characters.")
    private String otherName;
    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank(message = "Family Name is required")
    @Size(min = 1, max = 64, message = "First Name must not be longer than  64 characters")
    private String familyName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Person firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Person otherName(String otherName) {
        this.otherName = otherName;
        return this;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Person familyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

}