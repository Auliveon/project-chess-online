package com.ChessOnline.game.elements;

public class Knight implements Figure {
    private String id;
    private int[] stepValue = {21,19,12,8,-8,-19,-21,-12};
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

    public Knight(String id) {
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
