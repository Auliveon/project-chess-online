package com.ChessOnline.controllers.viewControllers.registrtionControllers;

import com.ChessOnline.services.CurrentUserOnlineChecker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        request.getRemoteAddr();
        System.out.println("Connected: " + request.getRemoteAddr());
        if (CurrentUserOnlineChecker.online()){
            return "redirect:/";
        }

        else return "registrationTemplates/login";
    }
    @PostMapping("/login")
    public String loginPost(Model model) {
        return "registrationTemplates/register";
    }


}