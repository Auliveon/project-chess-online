package com.ChessOnline.controller;

import com.ChessOnline.service.db.IUserService;
import com.ChessOnline.service.game.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController

public class GameController {

    private Sender sender;

    private IUserService userService;

    public GameController(Sender sender, IUserService userService) {
        this.sender = sender;
        this.userService = userService;
    }

    @PostMapping("/ajax")
    public void getText(HttpServletResponse response, HttpServletRequest request) throws IOException, InterruptedException {
        sender.handler(request, response, userService);
    }
}
