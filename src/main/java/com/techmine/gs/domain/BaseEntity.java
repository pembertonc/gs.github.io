package com.techmine.gs.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Cedric Pemberton
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseEntity id(String id) {
        this.id = id;
        return this;
    }

}