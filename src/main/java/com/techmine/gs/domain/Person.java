package com.techmine.gs.domain;

import java.util.Objects;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
    @OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    private Contact contact = new Contact();

    public Person() {
    }

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

    public Optional<Contact> getContact() {
        return Optional.ofNullable(contact);
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Person contact(Contact contact) {
        this.contact = contact;
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
        final Person other = (Person) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        hash = 31 * hash + Objects.hashCode(this.getFirstName());
        hash = 31 * hash + Objects.hashCode(this.getOtherName());
        hash = 31 * hash + Objects.hashCode(this.getFamilyName());
        return hash;
    }

    @Override
    public String toString() {
        return "Person{" + " firstName=" + firstName + ", otherName=" + otherName + ", familyName=" + familyName + ", contact=" + contact + '}';
    }

}