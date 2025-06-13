package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(name = "error", required = false) String error,
                              @RequestParam(name = "logout", required = false) String logout) {

        ModelAndView mAV = new ModelAndView("auth/login"); 
        mAV.addObject("error", error);
        mAV.addObject("logout", logout);
        return mAV;
    }

    @GetMapping("/loginSuccess")
    public ModelAndView loginCheck() {
        return new ModelAndView("redirect:/index");
    }
}
