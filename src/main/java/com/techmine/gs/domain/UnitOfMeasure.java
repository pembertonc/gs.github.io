package com.techmine.gs.domain;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class UnitOfMeasure extends BaseEntity {

    @Basic(optional = false)
    @Column(length = 32)
    @NotBlank(message = "Name is required")
    private String unitName;
    @Basic
    @Column(length = 5)
    @NotBlank(message = "Simbol is required")
    private String symbol;

    public UnitOfMeasure() {
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public UnitOfMeasure unitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public UnitOfMeasure symbol(String symbol) {
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
        final UnitOfMeasure other = (UnitOfMeasure) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        hash = 31 * hash + Objects.hashCode(this.getUnitName());
        hash = 31 * hash + Objects.hashCode(this.getSymbol());
        return hash;
    }

    @Override
    public String toString() {
        return "UnitOfMeasure{" + " unitName=" + unitName + ", symbol=" + symbol + '}';
    }

}