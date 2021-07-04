package com.ChessOnline.game.elements;

public class WhitePawn implements Figure {
    private String id;
    private int[] stepValue = {1};
    private int varCount = 2;
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

    public void setVarCount(int varCount) {
        this.varCount = varCount;
    }

    public WhitePawn(String id) {
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
