package com.ChessOnline.game.gameFieldElements;

public class Bishop implements Figure {
    private String id;
    private int[] stepValue = {11, -11, 9, -9};
    private int varCount = 99;

    public int getVarCount() {
        return varCount;
    }


    public Bishop(String id) {
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
