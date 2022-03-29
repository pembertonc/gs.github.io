package com.techmine.gs.domain;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Cedric Pemberton
 */
@Entity
public class GasType extends BaseEntity {

    @Basic(optional = false)
    @Column(unique = true, nullable = false, length = 32)
    @NotBlank(message = "Name of gas is required")
    @Size(min = 1, max = 64, message = "Gas Type name can not be longer than 64 characters")
    private String gasName;
    @Basic(optional = false)
    @Column(unique = true, nullable = false, length = 16)
    @NotBlank(message = "Gas Symbol is required")
    @Size(min = 1, max = 3, message = "Gas Symbol can not be greater than 3 characters")
    private String symbol;

    public GasType(String gasName, String symbol) {
        this.gasName = gasName;
        this.symbol = symbol;
    }

    public GasType() {
    }

    public String getGasName() {
        return gasName;
    }

    public void setGasName(String gasName) {
        this.gasName = gasName;
    }

    public GasType gasName(String gasName) {
        this.gasName = gasName;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public GasType symbol(String symbol) {
        this.symbol = symbol;
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
        final GasType other = (GasType) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        hash = 31 * hash + Objects.hashCode(this.getGasName());
        hash = 31 * hash + Objects.hashCode(this.getSymbol());
        return hash;
    }

    @Override
    public String toString() {
        return "GasType{" + " id=" + id + '}';
    }

}