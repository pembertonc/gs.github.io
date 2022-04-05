package com.techmine.gs.domain;

import java.util.Objects;
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

    @Basic(optional = false)
    @Column(nullable = false, length = 16)
    @NotBlank(message = "Serial Number can not be blank")
    private String serialNumber;
    @Embedded
    private Measure cylinderSize = new Measure();
    /**
     * The Institution who owns the Cylinder
     */
    @OneToOne(optional = false)
    private Institution owner;
    @ManyToOne(optional = false)
    private GasType gasType;

    public Cylinder() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Cylinder serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public Measure getCylinderSize() {
        return cylinderSize;
    }

    public void setCylinderSize(Measure cylinderSize) {
        this.cylinderSize = cylinderSize;
    }

    public Cylinder cylinderSize(Measure cylinderSize) {
        this.cylinderSize = cylinderSize;
        return this;
    }

    public Institution getOwner() {
        return owner;
    }

    public void setOwner(Institution owner) {
        this.owner = owner;
    }

    /**
     * Set the Institution who owns the Cylinder
     *
     * @param owner {@link #owner}
     * @return {@link #Cylinder}
     */
    public Cylinder owner(Institution owner) {
        this.owner = owner;
        return this;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Objects.equals(getClass(), obj.getClass())) {
            return false;
        }
        final Cylinder other = (Cylinder) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        hash = 31 * hash + Objects.hashCode(this.getSerialNumber());
        hash = 31 * hash + Objects.hashCode(this.getCylinderSize());
        hash = 31 * hash + Objects.hashCode(this.getOwner());
        hash = 31 * hash + Objects.hashCode(this.getGasType());
        return hash;
    }

    @Override
    public String toString() {
        return "Cylinder{" + " serialNumber=" + serialNumber + ", cylinderSize=" + cylinderSize + ", owner=" + owner + ", gasType=" + gasType + '}';
    }

}