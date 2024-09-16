package com.danielcarnauba.learning.landon_hotel.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getWelcome(@RequestParam(value = "name", required = false) String name) {
        String greeting = "Hello ";
        if (StringUtils.isNotBlank(name)) {
            greeting = greeting + name + "!";
        } else {
            greeting = greeting + "world!";
        }
        return "<h1>" + greeting + "</h1>";
    }
}