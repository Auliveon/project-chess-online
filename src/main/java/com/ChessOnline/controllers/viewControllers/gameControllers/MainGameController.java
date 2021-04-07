package com.ChessOnline.controllers.viewControllers.gameControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainGameController {
    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {
        return "index";
    }
}
