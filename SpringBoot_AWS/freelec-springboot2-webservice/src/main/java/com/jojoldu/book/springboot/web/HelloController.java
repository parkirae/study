package com.jojoldu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //JSON을 반환합니다.
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
