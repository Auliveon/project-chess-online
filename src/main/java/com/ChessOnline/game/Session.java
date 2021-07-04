package com.ChessOnline.game;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class Session {
    private String sessionName;
    private List<String> sessionPlayersNames = new ArrayList<>();
    private List<ModPlayer> sessionPlayers = new ArrayList<>();
    private Step[] stepsHistory;
    private ModPlayer p1;
    private ModPlayer p2;
    private GameEngine gameEngine = new GameEngine();
    private ArrayDeque<Answer> p1Requests = new ArrayDeque<>();
    private ArrayDeque<Answer> p2Requests = new ArrayDeque<>();

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
        this.p1Requests.add(new Answer("a", "You", "startGame", null, "white"));
        this.p2Requests.add(new Answer("a", "", "startGame", null, "black"));

    }

    public void requestHandler(String stringRequest, String name, HttpServletResponse response) throws IOException {
        //System.out.println(stringRequest);


        if (stringRequest.equals("\"status\"")) {
            if (p1.getUserName().equals(name)) {
                ObjectMapper objectMapper = new ObjectMapper();
                if(p1Requests.size() > 0) {
                    response.getWriter().write(objectMapper.writeValueAsString(p1Requests.poll()));
                } else {
                    response.getWriter().write(objectMapper.writeValueAsString(new Answer(null, null,
                            null, null, null)));

                }
            }

            else if (p2.getUserName().equals(name)) {
                ObjectMapper objectMapper = new ObjectMapper();
                if(p2Requests.size() > 0) {
                    response.getWriter().write(objectMapper.writeValueAsString(p2Requests.poll()));
                } else {
                    response.getWriter().write(objectMapper.writeValueAsString(new Answer(null, null,
                            null, null, null)));

                }
            }

        }

        if (stringRequest.equals("\"getGameField\"")) {
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(gameEngine));

        }

        if (stringRequest.startsWith("\"step")) {
            String answer = gameEngine.makeTurn(stringRequest);
            if(answer.equals("Yes")) {
                if (name.equals(p1.getUserName())) {
                    if(gameEngine.checkMat().equals("white")) {
                        p2Requests.add(new Answer("Lose", null, null, null, null));
                        p1Requests.add(new Answer("Win", null, null, null, null));
                    }
                    p2Requests.add(new Answer(null, "You", "updateField", null, null));
                    p1Requests.add(new Answer(null, "", "updateField", null, null));

                }

                if (name.equals(p2.getUserName())) {
                    if(gameEngine.checkMat().equals("black")) {
                        p1Requests.add(new Answer("Lose", null, null, null, null));
                        p2Requests.add(new Answer("Win", null, null, null, null));
                    }
                    p1Requests.add(new Answer(null, "You", "updateField", null, null));
                    p2Requests.add(new Answer(null, "", "updateField", null, null));
                }
            }
            else if(answer.equals("No")) {
                if (name.equals(p1.getUserName())) {
                    p2Requests.add(new Answer(null, "", "updateField", null, null));
                    p1Requests.add(new Answer(null, "You", "updateField", null, null));
                }

                if (name.equals(p2.getUserName())) {
                    p1Requests.add(new Answer(null, "", "updateField", null, null));
                    p2Requests.add(new Answer(null, "You", "updateField", null, null));
                }
            }
        }

        if (stringRequest.startsWith("\"getAvailableSteps")) {
            //System.out.println(stringRequest);
            String figure = convertRequest(stringRequest).split("-")[1];
            //System.out.println(figure);
            StringBuilder sb = new StringBuilder();
            for(String s  : gameEngine.getAvailableSteps(figure)) {
                sb.append(s + "-");
            }
            response.getWriter().write(sb.toString());

            }
    }

    public String convertRequest(String req) {
        String convertedStep = req.substring(1, req.length()-1);
        return convertedStep;
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
        for (ModPlayer pl : sessionPlayers) {
            if (!pl.getUserName().equals(player.getUserName())) return pl.getUserName();
        }
        return null;
    }
}
