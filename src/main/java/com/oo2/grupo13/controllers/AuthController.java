package com.oo2.grupo13.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo13.entities.Usuario;
import com.oo2.grupo13.helpers.ViewRouteHelper;

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
    public RedirectView loginCheck() {
        RedirectView redirectView = null;
        Usuario user = ((Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(user.getRol().getRol().name().equals("ADMIN")) {
            redirectView = new RedirectView("/" + ViewRouteHelper.USUARIO_INDEX);
        } else if(user.getRol().getRol().name().equals("CLIENTE")) {
            redirectView = new RedirectView("/" + ViewRouteHelper.INICIO_CLIENTE);
        } else if(user.getRol().getRol().name().equals("SOPORTE")) {
            redirectView = new RedirectView("/" + ViewRouteHelper.INICIO_SOPORTE);
        }
        return redirectView;
    }
}
