package com.itransition.rpcserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Getter
@Schema(description = "result wrapper")
public class Wrapper<T> {

    @Schema(description = "identifier operation - true/false ")
    private final boolean success;

    @Schema(description = "return result")
    private final T result;

    @Schema(description = "error message")
    private final String error;

    public Wrapper(T result){
        this.success = true;
        this.result = result;
        this.error = null;
    }

    public Wrapper(Throwable cause, Class<T> intended) {
        this.success = false;
        this.result = null;
        this.error = cause.getMessage();
    }
}
