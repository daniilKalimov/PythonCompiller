package com.itransition.rpcserver.service;

import com.itransition.rpcserver.entity.Function;
import com.itransition.rpcserver.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class FunctionService {

    @Autowired
    FunctionRepository functionRepository;


    public Function createFunction(String code, String name) {
        return functionRepository.save(new Function(code, name));
    }


    public void deleteFunction(UUID id) {
        functionRepository.deleteById(id);
    }


    @Transactional
    public Function updateFunction(Function updated) {
        Function subject = functionRepository.findById(updated.getId()).get();
        subject.setCode(updated.getCode());
        subject.setName(updated.getName());
        subject = functionRepository.save(subject);
        return subject;
    }

    @Transactional
    public Function getFunction(UUID id) {
        return functionRepository.findById(id).get();
    }

    public List<Function> getAllFunctions() {
        return functionRepository.findAll();
    }
}
