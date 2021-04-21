package com.ChessOnline.game.gameFieldElements;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cell {
    private String id;

    public Cell(@JsonProperty(value = "name")String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
