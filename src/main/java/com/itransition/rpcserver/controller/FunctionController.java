package com.itransition.rpcserver.controller;


import com.itransition.rpcserver.dto.Wrapper;
import com.itransition.rpcserver.entity.Function;
import com.itransition.rpcserver.service.FunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/function")
@CrossOrigin("*")
@Api(description = "Function controller")
public class FunctionController {

    @Autowired
    FunctionService functionService;

    @PostMapping
    @ApiOperation("creating a new function")
    public Wrapper<Function> create(@RequestBody Function function) {
        return new Wrapper<>(functionService.createFunction(function.getCode(), function.getName()));
    }

    @GetMapping("/{id}")
    @ApiOperation("return function by id")
    public Wrapper<Function> get(@PathVariable UUID id) {
        return new Wrapper<>(functionService.getFunction(id));
    }

    @GetMapping
    @ApiOperation("return all functions")
    public Wrapper<List<Function>> getAll() {
        return new Wrapper<>(functionService.getAllFunctions());
    }

    @PutMapping
    @ApiOperation("update function ")
    public Wrapper<Function> update(@RequestBody Function function) {
        return new Wrapper<>(functionService.updateFunction(function));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("delete function")
    public void delete(@PathVariable UUID id) {
        functionService.deleteFunction(id);
    }
}
