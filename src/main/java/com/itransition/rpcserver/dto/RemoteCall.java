package com.itransition.rpcserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Schema(description = "remote call")
public class RemoteCall<T> {

    @Schema(description = "remote call id")
    private UUID id;

    @Schema(description = "id function")
    private String function;

    @Schema(description = "parameters remote call")
    private Map<String, Object> parameters;

    @Schema(description = "return result")
    private T result;

    @Schema(description = "exeption")
    private Throwable exception;

    public RemoteCall() {
        this.parameters = new ConcurrentHashMap<>();
        this.id = UUID.randomUUID();
    }

    public RemoteCall(String function) {
        this();
        this.function = function;
    }

    public boolean isSuccessful() {
        return exception == null;
    }

    public Wrapper asWrapper() {
        if (isSuccessful()) { return new Wrapper<>(result.toString());}
        return new Wrapper<>(exception, String.class);
    }
}
