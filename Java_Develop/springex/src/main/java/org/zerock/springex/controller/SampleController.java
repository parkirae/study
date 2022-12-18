package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello...");
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1...");
        log.info("name: " + name);
        log.info("age: " + age);
    }
}
