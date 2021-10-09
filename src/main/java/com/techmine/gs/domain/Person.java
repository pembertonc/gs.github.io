package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * @author Cedric Pemberton
 */
@Entity
public class Person extends BaseEntity {

    @Basic
    private String firstName;
    @Basic
    private String otherName;
    @Basic
    private String familyName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

}