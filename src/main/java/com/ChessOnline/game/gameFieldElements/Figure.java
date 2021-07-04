package com.ChessOnline.game.gameFieldElements;

public interface Figure {
    String getId();
    int[] getStepValue();
    int getVarCount();
    int getStepsCount();
    void setStepsCount(int stepsCount);



}
