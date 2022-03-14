package com.itransition.rpcserver.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

/**
 * Entity superclass for entities which carry displayable name
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Named extends Persistent {

    @Schema(description = "name function")
    protected String name;

    protected Named() {
        super();
    }

    protected Named(String name) {
        this();
        this.name = name;
    }
}
