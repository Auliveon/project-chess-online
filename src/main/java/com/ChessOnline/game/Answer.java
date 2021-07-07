package com.ChessOnline.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private String lastStep;
    private String whoTurn;
    private String message;
    private GameEngine gameEngine;
    private String side;
    private String enemyName;

    public Answer(@JsonProperty(value = "lastStep") String lastStep,
                  @JsonProperty(value = "whoTurn") String whoTurn,
                  @JsonProperty(value = "mess") String message,
                  @JsonProperty(value = "gameField") GameEngine gameEngine,
                  @JsonProperty(value = "side") String side,
                  @JsonProperty(value = "enemyName") String enemyName) {
        this.lastStep = lastStep;
        this.whoTurn = whoTurn;
        this.message = message;
        this.gameEngine = gameEngine;
        this.side = side;
        this.enemyName = enemyName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getLastStep() {
        return lastStep;
    }

    public void setLastStep(String type) {
        this.lastStep = type;
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
