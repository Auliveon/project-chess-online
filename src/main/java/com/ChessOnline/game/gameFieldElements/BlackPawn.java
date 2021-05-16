package com.ChessOnline.game.gameFieldElements;

public class BlackPawn implements Figure {
    private String id;
    private int[] stepValue = {-1};
    private int varCount = 2;

    public int getVarCount() {
        return varCount;
    }

    public BlackPawn(String id) {
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


