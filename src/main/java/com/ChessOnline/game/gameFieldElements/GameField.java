package com.ChessOnline.game.gameFieldElements;

import java.util.HashMap;
import java.util.Map;

public class GameField {
    private Map<Cell, Figure> gameField = new HashMap<>();
    public GameField() {

        this.gameField.put(new Cell("a1"), new Figure("blackRook1"));
        this.gameField.put(new Cell("a2"), new Figure("blackPawn1"));
        this.gameField.put(new Cell("a3"), null);
        this.gameField.put(new Cell("a4"), null);
        this.gameField.put(new Cell("a5"), null);
        this.gameField.put(new Cell("a6"), null);
        this.gameField.put(new Cell("a7"), new Figure("whitePawn1"));
        this.gameField.put(new Cell("a8"), new Figure("whiteRook1"));

        this.gameField.put(new Cell("b1"), new Figure("blackKnight1"));
        this.gameField.put(new Cell("b2"), new Figure("blackPawn2"));
        this.gameField.put(new Cell("b3"), null);
        this.gameField.put(new Cell("b4"), null);
        this.gameField.put(new Cell("b5"), null);
        this.gameField.put(new Cell("b6"), null);
        this.gameField.put(new Cell("b7"), new Figure("whitePawn2"));
        this.gameField.put(new Cell("b8"), new Figure("whiteKnight1"));

        this.gameField.put(new Cell("c1"), new Figure("blackBishop1"));
        this.gameField.put(new Cell("c2"), new Figure("blackPawn3"));
        this.gameField.put(new Cell("c3"), null);
        this.gameField.put(new Cell("c4"), null);
        this.gameField.put(new Cell("c5"), null);
        this.gameField.put(new Cell("c6"), null);
        this.gameField.put(new Cell("c7"), new Figure("whitePawn3"));
        this.gameField.put(new Cell("c8"), new Figure("whiteBishop1"));

        this.gameField.put(new Cell("d1"), new Figure("blackQueen1"));
        this.gameField.put(new Cell("d2"), new Figure("blackPawn4"));
        this.gameField.put(new Cell("d3"), null);
        this.gameField.put(new Cell("d4"), null);
        this.gameField.put(new Cell("d5"), null);
        this.gameField.put(new Cell("d6"), null);
        this.gameField.put(new Cell("d7"), new Figure("whitePawn4"));
        this.gameField.put(new Cell("d8"), new Figure("whiteQueen4"));

        this.gameField.put(new Cell("e1"), new Figure("blackKing1"));
        this.gameField.put(new Cell("e2"), new Figure("blackPawn5"));
        this.gameField.put(new Cell("e3"), null);
        this.gameField.put(new Cell("e4"), null);
        this.gameField.put(new Cell("e5"), null);
        this.gameField.put(new Cell("e6"), null);
        this.gameField.put(new Cell("e7"), new Figure("whitePawn5"));
        this.gameField.put(new Cell("e8"), new Figure("whiteKing1"));

        this.gameField.put(new Cell("f1"), new Figure("blackBishop2"));
        this.gameField.put(new Cell("f2"), new Figure("blackPawn6"));
        this.gameField.put(new Cell("f3"), null);
        this.gameField.put(new Cell("f4"), null);
        this.gameField.put(new Cell("f5"), null);
        this.gameField.put(new Cell("f6"), null);
        this.gameField.put(new Cell("f7"), new Figure("whitePawn6"));
        this.gameField.put(new Cell("f8"), new Figure("whiteBishop2"));

        this.gameField.put(new Cell("g1"), new Figure("blackKnight2"));
        this.gameField.put(new Cell("g2"), new Figure("blackPawn7"));
        this.gameField.put(new Cell("g3"), null);
        this.gameField.put(new Cell("g4"), null);
        this.gameField.put(new Cell("g5"), null);
        this.gameField.put(new Cell("g6"), null);
        this.gameField.put(new Cell("g7"), new Figure("whitePawn7"));
        this.gameField.put(new Cell("g8"), new Figure("whiteKnight2"));

        this.gameField.put(new Cell("h1"), new Figure("blackRook2"));
        this.gameField.put(new Cell("h2"), new Figure("blackPawn8"));
        this.gameField.put(new Cell("h3"), null);
        this.gameField.put(new Cell("h4"), null);
        this.gameField.put(new Cell("h5"), null);
        this.gameField.put(new Cell("h6"), null);
        this.gameField.put(new Cell("h7"), new Figure("whitePawn8"));
        this.gameField.put(new Cell("h8"), new Figure("whiteRook2"));

    }

    public Map<Cell, Figure> getGameField() {
        return gameField;
    }

    public void setGameField(Map<Cell, Figure> gameField) {
        this.gameField = gameField;
    }

    public void makeTurn(String figureID, String cellID) {

    }
}
