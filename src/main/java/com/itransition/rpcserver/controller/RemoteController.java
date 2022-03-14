package com.itransition.rpcserver.controller;

import com.itransition.rpcserver.contract.RemoteScript;
import com.itransition.rpcserver.dto.RemoteCall;
import com.itransition.rpcserver.dto.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/function/execute")
@CrossOrigin("*")
@Api(description = "Remote call controller")
public class RemoteController {

    @Autowired
    RemoteScript remoteScript;

    @PostMapping
    @ApiOperation("remote call function by id")
    public Wrapper remoteCall(@RequestBody RemoteCall remoteCall) {
        remoteScript.execute(remoteCall);
        return remoteCall.asWrapper();
    }
}
