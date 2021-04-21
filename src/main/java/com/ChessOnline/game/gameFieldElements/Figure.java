package com.ChessOnline.game.gameFieldElements;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Figure {
    private String id;

    public Figure(@JsonProperty(value = "id")String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
