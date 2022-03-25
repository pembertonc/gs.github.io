package com.techmine.gs.domain;

import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class Measure {

    @Basic
    @Column(nullable = false)
    @NotNull
    @Positive(message = "Value must be greater than or equal to zero")
    private double value;
    @ManyToOne
    private UinitOfMeasure uinitOfMeasure;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Measure value(double value) {
        this.value = value;
        return this;
    }

    public Optional<UinitOfMeasure> getUinitOfMeasure() {
        return Optional.ofNullable(uinitOfMeasure);
    }

    public void setUinitOfMeasure(UinitOfMeasure uinitOfMeasure) {
        this.uinitOfMeasure = uinitOfMeasure;
    }

    public Measure uinitOfMeasure(UinitOfMeasure uinitOfMeasure) {
        this.uinitOfMeasure = uinitOfMeasure;
        return this;
    }

}