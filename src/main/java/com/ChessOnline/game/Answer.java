package com.ChessOnline.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private String type;
    private String whoTurn;
    private String message;
    private GameEngine gameEngine;
    private String side;

    public Answer(@JsonProperty(value = "type") String type,
                  @JsonProperty(value = "whoTurn") String whoTurn,
                  @JsonProperty(value = "mess") String message,
                  @JsonProperty(value = "gameField") GameEngine gameEngine,
                  @JsonProperty(value = "side") String side) {
        this.type = type;
        this.whoTurn = whoTurn;
        this.message = message;
        this.gameEngine = gameEngine;
        this.side = side;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWhoTurn() {
        return whoTurn;
    }

    public void setWhoTurn(String whoTurn) {
        this.whoTurn = whoTurn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
}
