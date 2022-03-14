package com.itransition.rpcserver.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "Function entity")
public class Function extends Named {

    @Column(columnDefinition = "CLOB")
    @Schema(description = "this python code")
    protected String code;

    public Function(String code, String name) {
        super(name);
        this.code = code;
    }

    public Function() {
        super();
    }
}
