package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oo2.grupo13.helpers.ViewRouteHelper;

@Controller
@RequestMapping ("/")
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return ViewRouteHelper.HOME;
    }
}
