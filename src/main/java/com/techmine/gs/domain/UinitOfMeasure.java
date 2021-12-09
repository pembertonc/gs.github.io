package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * @author Cedric Pemberton
 */
@Entity
public class UinitOfMeasure extends BaseEntity {

    @Basic(optional = false)
    @Column(length = 32)
    @NotBlank(message = "Name is required")
    private String name;
    @Basic
    @Column(length = 5)
    @NotBlank(message = "Simbol is required")
    private String symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UinitOfMeasure name(String name) {
        this.name = name;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public UinitOfMeasure symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

}