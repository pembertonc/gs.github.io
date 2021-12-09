package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

/**
 * @author Cedric Pemberton
 */
@Entity
public class Cylinder extends BaseEntity {

    @OneToOne
    private GasType gasType;
    @ManyToOne
    private Owner owner;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Measure getCapacitity() {
        return capacitity;
    }

    public void setCapacitity(Measure capacitity) {
        this.capacitity = capacitity;
    }

    @Basic(optional = false)
    @Column(updatable = false, length = 32)
    @NotBlank
    private String serialNumber;

    @Embedded
    private Measure capacitity;

    public GasType getGasType() {
        return gasType;
    }

    public void setGasType(GasType gasType) {
        this.gasType = gasType;
    }

    public Cylinder gasType(GasType gasType) {
        this.gasType = gasType;
        return this;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}