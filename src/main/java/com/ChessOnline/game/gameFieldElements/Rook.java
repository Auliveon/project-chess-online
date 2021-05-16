package com.ChessOnline.game.gameFieldElements;

public class Rook implements Figure {

    private String id;
    private int[] stepValue = {10, -10, 1, -1};
    private int varCount = 99;
    public int getVarCount() {
        return varCount;
    }

    public Rook(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getStepValue() {
        return stepValue;
    }

    public void setStepValue(int[] stepValue) {
        this.stepValue = stepValue;
    }
}
