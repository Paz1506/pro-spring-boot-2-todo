package com.apress.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Zaytsev
 */
@RestController
public class WebController {

    @GetMapping
    public String index(){
        return "Hello Spring Boot";
    }
}
