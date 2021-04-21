package com.ChessOnline.game;

import com.ChessOnline.game.gameFieldElements.GameField;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private String sessionName;
    private List<String> sessionPlayersNames = new ArrayList<>();
    private List<ModPlayer> sessionPlayers = new ArrayList<>();
    private Step[] stepsHistory;
    private ModPlayer p1;
    private ModPlayer p2;
    private GameField gameField;
    public Session(ModPlayer p1, ModPlayer p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p1.setSide("white");
        this.p2.setSide("black");
        this.sessionPlayers.add(this.p1);
        this.sessionPlayers.add(this.p2);
        this.sessionPlayersNames.add(p1.getUserName());
        this.sessionPlayersNames.add(p2.getUserName());
        this.sessionName = p1.getUserName() + "-" + p2.getUserName();

    }

    public void requestHandler(String stringRequest, String name, HttpServletResponse response) throws IOException {

        if(stringRequest.equals("\"session-getSide\"")) {
            for(ModPlayer player : sessionPlayers) {
                if(player.getUserName().equals(name)) {
                    response.getWriter().write(player.getSide());
                }
            }
        }

        if(stringRequest.equals("\"session-getGameField\"")) {
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(gameField));
        }



    }

    public Session() {

    }

    public List<ModPlayer> getSessionPlayers() {
        return sessionPlayers;
    }

    public String getSessionName() {
        return sessionName;
    }

    public List<String> getSessionPlayersNames() {
        return sessionPlayersNames;
    }

    public Step[] getStepsHistory() {
        return stepsHistory;
    }
    public String getAnotherModPlayer(ModPlayer player) {
        for(ModPlayer pl:sessionPlayers) {
            if(!pl.getUserName().equals(player.getUserName())) return pl.getUserName();
        }
        return null;
    }
}
