package com.ChessOnline.controllers.GameController;

import com.ChessOnline.game.Sender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class GameController {
    Sender sender = new Sender();

    @PostMapping("/ajax")
    public void getText(HttpServletResponse response, HttpServletRequest request) throws IOException {
        sender.handler(request, response);
    }
}
