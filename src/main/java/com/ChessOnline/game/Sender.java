package com.ChessOnline.game;

import com.ChessOnline.game.gameFieldElements.GameField;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class Sender {

    private String name;

    public Sender() {

    }

    public void handler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String req = request.getReader().readLine();

        if (req.equals("\"ready\"")) {
            if (!UniqueSessions.checkModPlayerInSessionList(new ModPlayer(request)) && !UniqueUserQueue.checkModPlayerInUserQueue(new ModPlayer(request))) {
                UniqueUserQueue.uniqueAdd(new ModPlayer(request));
                response.getWriter().write("added in queue");
            }

        }

        if (req.equals("\"checkMeOnQueue\"")) {
            if ((UniqueSessions.checkModPlayerInSessionList(new ModPlayer(request)) || UniqueUserQueue.checkModPlayerInUserQueue(new ModPlayer(request)))) {
                response.getWriter().write("yes");
            }


        }
        if (req.equals("\"checkGame\"")) {
            if (UniqueSessions.checkModPlayerInSessionList(new ModPlayer(request)) && !UniqueUserQueue.checkModPlayerInUserQueue(new ModPlayer(request))) {
               response.getWriter().write("Your enemy: " + UniqueSessions.getSessionByModPlayer(new ModPlayer(request)).getAnotherModPlayer(new ModPlayer(request)));
            } else {
                response.getWriter().write("no");
            }

        }

        if(req.startsWith("\"session")) {
            if(UniqueSessions.checkModPlayerInSessionList(new ModPlayer(request))) {
                ModPlayer player = new ModPlayer(request);
                UniqueSessions.getSessionByModPlayer(player).requestHandler(req, request.getRemoteUser(), response);


            }
        }

        if(req.equals("\"getGameField\"")) {
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(new GameField()));
        }

    }
}
