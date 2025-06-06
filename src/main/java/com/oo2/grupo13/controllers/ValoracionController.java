package com.oo2.grupo13.controllers;
import com.oo2.grupo13.services.IValoracionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/valoraciones")
public class ValoracionController {

    private final IValoracionService valoracionService;

    public ValoracionController(IValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }
    
}
