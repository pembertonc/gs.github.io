package com.techmine.gs.domain;

import java.util.Optional;
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

    @Basic(optional = false)
    @Column(nullable = false, length = 16)
    @NotBlank(message = "Serial Number can not be blank")
    private String serialNumber;
    @Embedded
    private Measure cylinderSize;
    @OneToOne
    private GasType gasType;
    @OneToOne
    private Institution institution;

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

    public Optional<Measure> getCylinderSize() {
        return Optional.ofNullable(cylinderSize);
    }

    public void setCylinderSize(Measure cylinderSize) {
        this.cylinderSize = cylinderSize;
    }

    public Cylinder cylinderSize(Measure cylinderSize) {
        this.cylinderSize = cylinderSize;
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

}