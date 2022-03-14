package com.itransition.rpcserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Schema(description = "list function, id - name")
public class FunctionList {

    @Schema(description = "map id-name")
    private Map<UUID, String> functions;

    public FunctionList(){
        functions = new HashMap<>();
    }

}
