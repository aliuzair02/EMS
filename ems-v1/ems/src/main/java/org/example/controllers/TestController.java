package org.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test1")
    public String test1(){
        return "<h1>TEST1</h1>";
    }

    @GetMapping("/test2/{x}")
    public String test2(@PathVariable("x") String x){
        return x;
    }

    @GetMapping("/test3")
    public String test3(){
        return "<h1>HELLO WORLD</h1>";
    }

    @GetMapping("/test4")
    public String test4(){
        return "<h1>TEST4</h1>";
    }


}
