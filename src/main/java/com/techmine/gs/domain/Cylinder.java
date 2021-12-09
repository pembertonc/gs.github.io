package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

/**
 * @author Cedric Pemberton
 */
@Entity
public class Cylinder extends BaseEntity {

    @OneToOne
    private GasType gasType;

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

    @OneToOne
    private Institution institution;

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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Cylinder institution(Institution institution) {
        this.institution = institution;
        return this;
    }

}