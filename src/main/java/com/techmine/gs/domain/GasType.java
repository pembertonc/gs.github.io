package com.techmine.gs.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * @author Cedric Pemberton
 */
@Entity
public class GasType extends BaseEntity {

    @Basic
    @Column(unique = true, length = 32)
    @NotBlank(message = "Name of Gass is required")
    private String name;
    @Basic
    @Column(unique = true, length = 5)
    @NotBlank(message = "Simbol for gas is required")
    private String symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}