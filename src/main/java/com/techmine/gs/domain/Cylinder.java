package com.techmine.gs.domain;

import java.util.Objects;
import java.util.Optional;
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
    @OneToOne
    private Institution institution;
    @ManyToOne
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

    public Optional<Institution> getInstitution() {
        return Optional.ofNullable(institution);
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Cylinder institution(Institution institution) {
        this.institution = institution;
        return this;
    }

    public Optional<GasType> getGasType() {
        return Optional.ofNullable(gasType);
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
        hash = 31 * hash + Objects.hashCode(this.getInstitution().orElse(null));
        hash = 31 * hash + Objects.hashCode(this.getGasType().orElse(null));
        return hash;
    }

    @Override
    public String toString() {
        return "Cylinder{" + " serialNumber=" + serialNumber + ", cylinderSize=" + cylinderSize + ", institution=" + institution + ", gasType=" + gasType + '}';
    }

}