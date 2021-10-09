package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Cedric Pemberton
 */
@Entity
public class Owner extends BaseEntity {

    @Basic
    private String name;
    @OneToOne
    private Address address;
    @OneToOne
    private Contact contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}