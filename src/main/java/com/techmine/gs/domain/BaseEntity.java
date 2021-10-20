package com.techmine.gs.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Cedric Pemberton
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}