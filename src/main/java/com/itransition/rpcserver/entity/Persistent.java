package com.itransition.rpcserver.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Base persistent entity superclass
 */
@MappedSuperclass
public abstract class Persistent {
    @Id
    @Getter
    @Schema(description = "function id")
    protected final UUID id;

    protected Persistent(UUID id) {
        this.id = id;
    }

    protected Persistent() {
        this(UUID.randomUUID());
    }
}
