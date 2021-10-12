package com.ChessOnline.controller.gameController;

import com.ChessOnline.service.game.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class GameController {

    private Sender sender;

    public GameController(Sender sender) {
        this.sender = sender;
    }

    @PostMapping("/makeStep")
    public ResponseEntity<?> getText(HttpServletResponse response, HttpServletRequest request) throws IOException {
       return ResponseEntity.ok().build();
        //sender.handler(sender);
    }

}
