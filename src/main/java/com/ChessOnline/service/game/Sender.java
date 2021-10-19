package com.ChessOnline.service.game;

import com.ChessOnline.game.Player;
import com.ChessOnline.service.db.IUserService;
import com.ChessOnline.util.UniqueSessions;
import com.ChessOnline.util.UniqueUserQueue;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Service
public class Sender {

    private String name;

    public Sender() {

    }

    public void handler(HttpServletRequest request, HttpServletResponse response, IUserService userService) throws IOException, InterruptedException {


        String stringRequest = request.getReader().readLine();

        if (UniqueSessions.checkModPlayerInSessionList(new Player(request))) {
            Objects.requireNonNull(UniqueSessions.getSessionByModPlayer
                    (new Player(request))).requestHandler(stringRequest, request.getRemoteUser(), response);
        } else if (stringRequest.equals("\"addMeOnQueue\"")) {
            UniqueUserQueue.uniqueAdd(new Player(request), userService);
            response.getWriter().write("added");
        } else if (stringRequest.equals("removeMeFromQueue")) {
            UniqueUserQueue.removeFromQueue(request.getRemoteUser());
            response.getWriter().write("removed");
        } else if (UniqueUserQueue.checkModPlayerInUserQueue(new Player(request))) {

            UniqueUserQueue.handler(stringRequest, request.getRemoteUser(), response);

        } else {
            response.getWriter().write("no");

        }
    }
}
