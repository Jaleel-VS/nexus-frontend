package com.nexus.nexusapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "Welcome to Nexus API.  at <a href=http://localhost:8080/swagger-ui/index.html#/ target=_blank>Read docs</a>";
    }
}