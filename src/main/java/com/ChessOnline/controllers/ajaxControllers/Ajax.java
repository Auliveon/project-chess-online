package com.ChessOnline.controllers.ajaxControllers;

import com.ChessOnline.game.ModPlayer;
import com.ChessOnline.game.Sender;
import com.ChessOnline.game.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class Ajax {
    Sender sender = new Sender();

    @PostMapping("/ajax")
    public void getText(HttpServletResponse response, HttpServletRequest request) throws IOException {
        sender.handler(request, response);
    }
}
