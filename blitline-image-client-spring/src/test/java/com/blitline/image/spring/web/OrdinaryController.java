package com.blitline.image.spring.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdinaryController {
    @RequestMapping("/times2")
    public FooHolder doubleIt(@RequestBody FooHolder input) {
        return new FooHolder(2 * input.getFoo());
    }
}
