package com.ChessOnline.controllers.viewControllers.regController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegController {

    @GetMapping("/register")
    public String register() {
        return "registrationTemplates/register";
    }
}
