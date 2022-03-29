package com.techmine.gs.domain;

import java.util.Objects;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Cedric Pemberton
 */
@Entity
public class Institution extends BaseEntity {

    @Basic(optional = false)
    @Column(nullable = false, length = 64)
    @NotEmpty(message = "Institution Name is required")
    @Size(min = 1, max = 64, message = "Institutino name must not exceed 64 characters")
    private String name;
    @OneToOne(optional = false)
    private Address address;
    @OneToOne
    private Contact contact;
    @OneToOne(mappedBy = "owner")
    private Cylinder cylinder;

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

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Institution address(Address address) {
        this.address = address;
        return this;
    }

    public Optional<Contact> getContact() {
        return Optional.ofNullable(contact);
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Institution contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Optional<Cylinder> getCylinder() {
        return Optional.ofNullable(cylinder);
    }

    public void setCylinder(Cylinder cylinder) {
        this.cylinder = cylinder;
    }

    public Institution cylinder(Cylinder cylinder) {
        this.cylinder = cylinder;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Objects.equals(getClass(), obj.getClass())) {
            return false;
        }
        final Institution other = (Institution) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        hash = 31 * hash + Objects.hashCode(this.getName());
        return hash;
    }

    @Override
    public String toString() {
        return "Institution{" + " name=" + name + ", address=" + address + '}';
    }

}