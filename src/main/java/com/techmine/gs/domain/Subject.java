package com.techmine.gs.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Cedric Pemberton
 */
@Entity
@NamedQuery(name = "Subject.findByUserName", query = "Select a from Subject a where a.userName=:userName")
public class Subject extends BaseEntity {

    @Basic(optional = false)
    @Column(unique = true, nullable = false, length = 64)
    @NotBlank(message = "User Name is required")
    private String userName;
    @Basic(optional = false)
    @Column(nullable = false, length = 12)
    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 12, message = "Password must be from 4 to 12 characters long.")
    private String password;
    @OneToOne
    private Person person;
    @ManyToMany
    private List<Role> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Subject userName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Subject password(String password) {
        this.password = password;
        return this;
    }

    public Optional<Person> getPerson() {
        return Optional.ofNullable(person);
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Subject person(Person person) {
        this.person = person;
        return this;
    }

    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Subject roles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public void addRole(Role role) {
        getRoles().add(role);
    }

    public void removeRole(Role role) {
        getRoles().remove(role);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Objects.equals(getClass(), obj.getClass())) {
            return false;
        }
        final Subject other = (Subject) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        hash = 31 * hash + Objects.hashCode(this.getUserName());
        hash = 31 * hash + Objects.hashCode(this.getPassword());
        return hash;
    }

    @Override
    public String toString() {
        return "Subject{" + " userName=" + userName + ", password=" + password + '}';
    }

}