package com.techmine.gs.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class Measure implements Serializable {

    @Basic
    @Column(nullable = false)
    @NotNull
    @Positive(message = "Value must be greater than or equal to zero")
    private double measureValue;
    @ManyToOne
    private UnitOfMeasure unitOfMeasure;

    public Measure() {
    }

    public double getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(double measureValue) {
        this.measureValue = measureValue;
    }

    public Measure measureValue(double measureValue) {
        this.measureValue = measureValue;
        return this;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Measure unitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
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
        final Measure other = (Measure) obj;
        if (Double.doubleToLongBits(this.getMeasureValue()) != Double.doubleToLongBits(other.getMeasureValue())) {
            return false;
        }
        if (!java.util.Objects.equals(this.getUnitOfMeasure(), other.getUnitOfMeasure())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.getMeasureValue()) ^ (Double.doubleToLongBits(this.getMeasureValue()) >>> 32));
        hash = 31 * hash + Objects.hashCode(this.getUnitOfMeasure());
        return hash;
    }

    @Override
    public String toString() {
        return "Measure{" + " measureValue=" + measureValue + ", unitOfMeasure=" + unitOfMeasure + '}';
    }

}