package com.isaque.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isaque.backend.model.Calculator;
import com.isaque.backend.service.HelloService;

@RestController
public class Hello {

    @Autowired
    private HelloService helloService;

    @GetMapping("/")
    public String hello() {
        return "Olá, Docker";
    }

    // @GetMapping("/sum")
    public Integer sum(@RequestParam("v1") Integer value1,
            @RequestParam("v2") Integer value2) {
        return value1 + value2;

    }

    @PostMapping("/sum")
    public Calculator sum(@RequestBody Calculator calculator) {
        calculator.setResult(calculator.getValue1() + calculator.getValue1());

        return helloService.sum(calculator);
    }
}
