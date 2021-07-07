package com.ChessOnline.game;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class Sender {

    private String name;

    public Sender() {

    }

    public void handler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String stringRequest = request.getReader().readLine();

        if(UniqueSessions.checkModPlayerInSessionList(new Player(request))) {
            Objects.requireNonNull(UniqueSessions.getSessionByModPlayer
                    (new Player(request))).requestHandler(stringRequest, request.getRemoteUser(), response);
        }

        else if(UniqueUserQueue.checkModPlayerInUserQueue(new Player(request))) {

            UniqueUserQueue.handler(stringRequest, request.getRemoteUser(), response);
        }

        else if(stringRequest.equals("\"addMeOnQueue\"")) {
            UniqueUserQueue.uniqueAdd(new Player(request));
            response.getWriter().write("added");
        } else {
            response.getWriter().write("no");
        }



    }
}
