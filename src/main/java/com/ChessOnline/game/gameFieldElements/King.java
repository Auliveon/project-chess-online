package com.ChessOnline.game.gameFieldElements;

public class King implements Figure {
    private String id;
    private int[] stepValue = {10,-10,1,-1,11,-11,9,-9};
    private int varCount = 1;
    private int stepsCount = 0;

    public int getStepsCount() {
        return stepsCount;
    }

    public void setStepsCount(int stepsCount) {
        this.stepsCount = stepsCount;
    }
    public int getVarCount() {
        return varCount;
    }

    public King(String id) {
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
