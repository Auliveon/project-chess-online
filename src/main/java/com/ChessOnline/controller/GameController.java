package com.ChessOnline.controller;

import com.ChessOnline.service.game.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController

@RequestMapping("/chessOnline/")
public class GameController {
    Sender sender = new Sender();

    @PostMapping("/ajax")
    public void getText(HttpServletResponse response, HttpServletRequest request) throws IOException {
        sender.handler(request, response);
    }
}
