package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Cedric Pemberton
 */
@Entity
public class Institution extends BaseEntity {

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

    public Institution name(String name) {
        this.name = name;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Institution address(Address address) {
        this.address = address;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Institution contact(Contact contact) {
        this.contact = contact;
        return this;
    }

}
