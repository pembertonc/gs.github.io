package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * @author CodeCamp4
 */
@Entity
public class Address extends BaseEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank
    private String street1;
    @Basic
    private String street2;
    @Basic
    private String city;
    @Basic
    private String country;

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public Address street1(String street1) {
        this.street1 = street1;
        return this;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public Address street2(String street2) {
        this.street2 = street2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address country(String country) {
        this.country = country;
        return this;
    }

}