package com.ChessOnline.game;

public class Step {
    private String startPosition;
    private String endPosition;
    private String figure;
    private String side;

    public Step(String startPosition, String endPosition, String figure, String side) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.figure = figure;
        this.side = side;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
