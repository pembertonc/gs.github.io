package com.techmine.gs.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @OneToMany
    @NotNull
    @Size(min = 1)
    private List<Contact> contacts = new ArrayList<Contact>();

    public Person() {
        contacts.add(new Contact());
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

    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Person contacts(List<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public void addContact(Contact contact) {
        getContacts().add(contact);
    }

    public void removeContact(Contact contact) {
        getContacts().remove(contact);
    }

}