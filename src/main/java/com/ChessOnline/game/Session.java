package com.ChessOnline.game;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class Session {
    private String sessionName;
    private List<String> sessionPlayersNames = new ArrayList<>();
    private List<Player> sessionPlayers = new ArrayList<>();
    private Step[] stepsHistory;
    private Player p1;
    private Player p2;
    private GameEngine gameEngine = new GameEngine();
    private ArrayDeque<Answer> p1Requests = new ArrayDeque<>();
    private ArrayDeque<Answer> p2Requests = new ArrayDeque<>();

    public Session(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p1.setSide("white");
        this.p2.setSide("black");
        this.sessionPlayers.add(this.p1);
        this.sessionPlayers.add(this.p2);
        this.sessionPlayersNames.add(p1.getUserName());
        this.sessionPlayersNames.add(p2.getUserName());
        this.sessionName = p1.getUserName() + "-" + p2.getUserName();
        this.p1Requests.add(new Answer(null, null, "gameReady", null, null, p2.getUserName()));
        this.p2Requests.add(new Answer(null, null, "gameReady", null, null, p1.getUserName()));
        this.p1Requests.add(new Answer(null, "You", "startGame", null, "white", p2.getUserName()));
        this.p2Requests.add(new Answer(null, "", "startGame", null, "black", p1.getUserName()));

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
                            null, null, null, p2.getUserName())));

                }
            }

            else if (p2.getUserName().equals(name)) {
                ObjectMapper objectMapper = new ObjectMapper();
                if(p2Requests.size() > 0) {
                    response.getWriter().write(objectMapper.writeValueAsString(p2Requests.poll()));
                } else {
                    response.getWriter().write(objectMapper.writeValueAsString(new Answer(null, null,
                            null, null, null, p1.getUserName())));

                }
            }

        }

        if (stringRequest.equals("\"getGameField\"")) {
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(gameEngine));

        }
        if (stringRequest.startsWith("\"getSide")) {
            if (p1.getUserName().equals(name)) {
                ObjectMapper objectMapper = new ObjectMapper();
                response.getWriter().write(objectMapper.writeValueAsString(new Answer(null, null,
                        "white", null, null, p2.getUserName())));
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                response.getWriter().write(objectMapper.writeValueAsString(new Answer(null, null,
                        "black", null, null, p1.getUserName())));
            }
        }

        if (stringRequest.startsWith("\"step")) {
            String step = convertRequest(stringRequest);
            String answer = gameEngine.makeTurn(stringRequest);
            if(answer.equals("Yes")) {
                if (name.equals(p1.getUserName())) {
                    if(gameEngine.checkMat().equals("white")) {
                        p2Requests.add(new Answer(null, null, "Lose", null, null, null));
                        p1Requests.add(new Answer(null, null, "Win", null, null, null));
                    }
                    p2Requests.add(new Answer(step.split("-")[1] + "-" + step.split("-")[3], "You", "updateField", null, null, p1.getUserName()));
                    p1Requests.add(new Answer(null, "", "updateField", null, null, p2.getUserName()));

                }

                if (name.equals(p2.getUserName())) {
                    if(gameEngine.checkMat().equals("black")) {
                        p1Requests.add(new Answer(null, null, "Lose", null, null, null));
                        p2Requests.add(new Answer(null, null, "Win", null, null, null));
                    }
                    p1Requests.add(new Answer(step.split("-")[1] + "-" + step.split("-")[3], "You", "updateField", null, null, null));
                    p2Requests.add(new Answer(null, "", "updateField", null, null, null));
                }
            }
            else if(answer.equals("No")) {
                if (name.equals(p1.getUserName())) {
                    p2Requests.add(new Answer(null, "", "updateField", null, null, null));
                    p1Requests.add(new Answer(null, "You", "updateField", null, null, null));
                }

                if (name.equals(p2.getUserName())) {
                    p1Requests.add(new Answer(null, "", "updateField", null, null, null));
                    p2Requests.add(new Answer(null, "You", "updateField", null, null, null));
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

    public List<Player> getSessionPlayers() {
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

    public String getAnotherModPlayer(Player player) {
        for (Player pl : sessionPlayers) {
            if (!pl.getUserName().equals(player.getUserName())) return pl.getUserName();
        }
        return null;
    }
}
