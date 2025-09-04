package com.isaque.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.isaque.backend.model.Calculator;

@Service
public class HelloService {
        
    public Calculator sum(@RequestBody Calculator calculator) {
        calculator.setResult(calculator.getValue1() + calculator.getValue1());
        
        return calculator;
    }
}
