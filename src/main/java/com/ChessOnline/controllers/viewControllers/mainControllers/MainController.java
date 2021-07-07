package com.ChessOnline.controllers.viewControllers.mainControllers;

import com.ChessOnline.game.Player;
import com.ChessOnline.game.UniqueSessions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "game/index";
    }

    @GetMapping("/login")
    public String login() {
        return "registrationTemplates/login";
    }

    @GetMapping("/findgame")
    public String findGame() {
        return "game/findGame";
    }
    @PostMapping("/getmodal")
    public ModelAndView getModal() {
        ModelAndView model = new ModelAndView("game/modal/findingGame");
        return model;
    }

    @GetMapping("/game")
    public String game(HttpServletRequest request) {
        if(UniqueSessions.checkModPlayerInSessionList(new Player(request))) {
            return "game/game";
        }
        else return "game/findGame";
    }

    @GetMapping("/test")
    public String test() {
        return "testPages/testPage1";
    }


}
