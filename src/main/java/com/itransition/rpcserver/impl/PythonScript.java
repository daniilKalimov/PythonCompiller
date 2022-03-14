package com.itransition.rpcserver.impl;

import com.itransition.rpcserver.contract.RemoteScript;
import com.itransition.rpcserver.dto.RemoteCall;
import com.itransition.rpcserver.entity.Function;
import com.itransition.rpcserver.repository.FunctionRepository;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Service
public class PythonScript implements RemoteScript {


    FunctionRepository functionRepository;

    public PythonScript(FunctionRepository functionRepository) {
        this.functionRepository = functionRepository;
    }

    @Override
    public List<String> enumerateFunctions() {
       List<Function> listFunctions = functionRepository.findAll();
       List<String> listUuidString = new LinkedList<>();
       listFunctions.forEach(s -> listUuidString.add(s.getId().toString()));
       return listUuidString;
    }

    @Override
    public <T> T execute(RemoteCall<T> call)  {
        try {
            Function function = functionRepository.findById(UUID.fromString(call.getFunction())).get();
            PythonInterpreter interpreter = new PythonInterpreter();
            call.getParameters().forEach((k, v) -> interpreter.set(k, v));
            call.setResult(
                    (T) interpreter.eval(
                        function.getCode()
                    )
            );
            return  call.getResult();
        } catch (Exception e){
            call.setException(e);
            return  call.getResult();
        }
    }
}
