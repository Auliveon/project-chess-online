package com.ChessOnline.game.gameFieldElements;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameField {
    private Map<String, Figure> gameField = new HashMap<>();
    public GameField() {

        this.gameField.put("a1", new Rook("whiteRook1"));
        this.gameField.put("a2", new WhitePawn("whitePawn1"));
        this.gameField.put("a3", null);
        this.gameField.put("a4", null);
        this.gameField.put("a5", null);
        this.gameField.put("a6", null);
        this.gameField.put("a7", new BlackPawn("blackPawn1"));
        this.gameField.put("a8", new Rook("blackRook1"));

        this.gameField.put("b1", new Knight("whiteKnight1"));
        this.gameField.put("b2", new WhitePawn("whitePawn2"));
        this.gameField.put("b3", null);
        this.gameField.put("b4", null);
        this.gameField.put("b5", null);
        this.gameField.put("b6", null);
        this.gameField.put("b7", new BlackPawn("blackPawn2"));
        this.gameField.put("b8", new Knight("blackKnight1"));

        this.gameField.put("c1", new Bishop("whiteBishop1"));
        this.gameField.put("c2", new WhitePawn("whitePawn3"));
        this.gameField.put("c3", null);
        this.gameField.put("c4", null);
        this.gameField.put("c5", null);
        this.gameField.put("c6", null);
        this.gameField.put("c7", new BlackPawn("blackPawn3"));
        this.gameField.put("c8", new Bishop("blackBishop1"));

        this.gameField.put("d1", new Queen("whiteQueen1"));
        this.gameField.put("d2", new WhitePawn("whitePawn4"));
        this.gameField.put("d3", null);
        this.gameField.put("d4", null);
        this.gameField.put("d5", null);
        this.gameField.put("d6", null);
        this.gameField.put("d7", new BlackPawn("blackPawn4"));
        this.gameField.put("d8", new Queen("blackQueen4"));

        this.gameField.put("e1", new King("whiteKing1"));
        this.gameField.put("e2", new WhitePawn("whitePawn5"));
        this.gameField.put("e3", null);
        this.gameField.put("e4", null);
        this.gameField.put("e5", null);
        this.gameField.put("e6", null);
        this.gameField.put("e7", new BlackPawn("blackPawn5"));
        this.gameField.put("e8", new King("blackKing1"));

        this.gameField.put("f1", new Bishop("whiteBishop2"));
        this.gameField.put("f2", new WhitePawn("whitePawn6"));
        this.gameField.put("f3", null);
        this.gameField.put("f4", null);
        this.gameField.put("f5", null);
        this.gameField.put("f6", null);
        this.gameField.put("f7", new BlackPawn("blackPawn6"));
        this.gameField.put("f8", new Bishop("blackBishop2"));

        this.gameField.put("g1", new Knight("whiteKnight2"));
        this.gameField.put("g2", new WhitePawn("whitePawn7"));
        this.gameField.put("g3", null);
        this.gameField.put("g4", null);
        this.gameField.put("g5", null);
        this.gameField.put("g6", null);
        this.gameField.put("g7", new BlackPawn("blackPawn7"));
        this.gameField.put("g8", new Knight("blackKnight2"));

        this.gameField.put("h1", new Rook("whiteRook2"));
        this.gameField.put("h2", new WhitePawn("whitePawn8"));
        this.gameField.put("h3", null);
        this.gameField.put("h4", null);
        this.gameField.put("h5", null);
        this.gameField.put("h6", null);
        this.gameField.put("h7", new BlackPawn("blackPawn8"));
        this.gameField.put("h8", new Rook("blackRook2"));
    }

    public Map<String, Figure> getGameField() {
        return gameField;
    }

    public void setGameField(Map<String, Figure> gameField) {
        this.gameField = gameField;
    }

    public String makeTurn(String step) {
        String convertedStep = step.substring(1, step.length()-1);
        System.out.println(convertedStep);
        String[] array = convertedStep.split("-");
        String cell = null;
        Figure figure = null;
        String sFigure = array[2];
        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getId().equals(sFigure)) {
                cell = entry.getKey();
                figure = entry.getValue();
            }
        }
        //System.out.println("dsdsdsd  " + cell + " " + figure);
        ArrayList<String> availableSteps = getSteps(sFigure);
        if (availableSteps.contains(array[3])) {
            gameField.put(array[3], figure);
            gameField.put(cell, null);
            System.out.println("Ok");
            return "Yes";
        } else {
            System.out.println("Cannot make step!");
            return "No";
        }

    }

    public ArrayList<String> getSteps(String step) {

        Figure figure = null;

        String sFigure = step;

        String currentCell = null;

        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getId().equals(sFigure)) {
                currentCell = entry.getKey();
                figure = entry.getValue();
            }
        }


        int iCell = vocabulary(currentCell);
        System.out.println(figure instanceof Knight);

        ArrayList<String> cells = new ArrayList<>();
        if (!(figure instanceof WhitePawn) && !(figure instanceof BlackPawn) && !(figure instanceof Knight)) {
            System.out.println(1);

            for (int element : figure.getStepValue()) {
                int tempICell = iCell;
                int varCount = figure.getVarCount();

                while (true) {
                    if (varCount == 0) {
                        break;
                    }
                    String tempValue = vocabulary(tempICell + element);

                    if (!tempValue.equals("wrong cell")) {
                        if (getFigureOnCell(vocabulary(tempValue)) != null && getSide(getFigureOnCell(vocabulary(tempValue))).equals(getSide(figure))) {
                            break;
                        } else if (getFigureOnCell(vocabulary(tempValue)) != null && !getSide(getFigureOnCell(vocabulary(tempValue))).equals(getSide(figure))) {
                            cells.add(tempValue);
                            break;
                        }
                        cells.add(tempValue);
                        tempICell += element;
                        varCount--;
                    } else {
                        break;
                    }
                }
            }
        } else if (figure instanceof WhitePawn || figure instanceof BlackPawn) {

            for (int element : figure.getStepValue()) {
                int tempICell = iCell;
                System.out.println(tempICell % 10);

                int varCount = figure.getVarCount();
                while (true) {
                    if (figure instanceof BlackPawn && iCell % 10 <= 5) {
                        --varCount;
                    }

                    if (figure instanceof WhitePawn && iCell % 10 >= 4) {
                        --varCount;
                    }

                    if (varCount <= 0) {
                        break;
                    }

                    String tempValue = vocabulary(tempICell + element);
                    if (!tempValue.equals("wrong cell")) {
                        if (getFigureOnCell(vocabulary(tempValue)) != null) {
                            break;
                        }
                        cells.add(tempValue);
                        tempICell += element;
                        varCount--;
                    } else {
                        break;
                    }

                }
            }

            if(figure instanceof WhitePawn) {
                if(!(vocabulary(iCell + (-9)).equals("wrong cell")) &&getFigureOnCell(iCell + (-9)) != null
                        && !getSide(figure).equals(getSide(getFigureOnCell(iCell + (-9))))) {
                    cells.add(vocabulary(iCell + (-9)));
                }
                if(!(vocabulary(iCell + (11)).equals("wrong cell")) &&getFigureOnCell(iCell + (11)) != null
                        && !getSide(figure).equals(getSide(getFigureOnCell(iCell + (11))))) {
                    cells.add(vocabulary(iCell + (11)));
                }

            }

            if(figure instanceof BlackPawn) {
                if(!(vocabulary(iCell + (-11)).equals("wrong cell")) &&getFigureOnCell(iCell + (-11)) != null
                        && !getSide(figure).equals(getSide(getFigureOnCell(iCell + (-11))))) {
                    cells.add(vocabulary(iCell + (-11)));
                }
                if(!(vocabulary(iCell + (9)).equals("wrong cell")) &&getFigureOnCell(iCell + (9)) != null
                        && !getSide(figure).equals(getSide(getFigureOnCell(iCell + (9))))) {
                    cells.add(vocabulary(iCell + (9)));
                }

            }


        } else if (figure instanceof Knight) {
            for(int element : figure.getStepValue()) {
                int tempCell  = iCell;
                int varCount = figure.getVarCount();

                while (true) {
                    String tempValue = vocabulary(tempCell + element);
                    if(varCount <= 0) {
                        break;
                    }
                    if(!(vocabulary(element + tempCell).equals("wrong cell"))) {
                        if (getFigureOnCell(vocabulary(tempValue)) != null && getSide(getFigureOnCell(vocabulary(tempValue))).equals(getSide(figure))) {
                            break;
                        } else if (getFigureOnCell(vocabulary(tempValue)) != null && !getSide(getFigureOnCell(vocabulary(tempValue))).equals(getSide(figure))) {
                            cells.add(tempValue);
                            break;
                        } else if(getFigureOnCell(vocabulary(tempValue)) == null) {
                            cells.add(tempValue);
                            break;
                        }
                    } else {
                        break;
                    }
                    System.out.println();
                }
            }

        }

        System.out.println(cells);
        return cells;


    }

    public int vocabulary(String cellID) {
        try {
            String[] vocabulary = {"a", "b", "c", "d", "e", "f", "g", "h",};
            int id = 0;
            for (int i = 0; i < vocabulary.length; i++) {
                if (cellID.substring(0, 1).equals(vocabulary[i])) {
                    id = i + 1;
                    break;
                }
            }
            String startPositionString = id + cellID.substring(1, 2);
            return Integer.parseInt(startPositionString);
        } catch (Exception e) {
            return -1;
        }
    }

    public String vocabulary(int cellID) {
        try {
            String[] vocabulary = {"a", "b", "c", "d", "e", "f", "g", "h",};
            String id = null;
            String sID = String.valueOf(cellID);
            Integer cel = cellID;
            int secondPar = Integer.parseInt(sID.substring(1, 2));
            if (!(secondPar > 0 & secondPar < 9)) throw new Exception();
            return vocabulary[Integer.parseInt(sID.substring(0, 1)) - 1] + sID.substring(1, 2);
        } catch (Exception e) {
            return "wrong cell";
        }

    }

    public Figure getFigureOnCell(int cellID) {
        String sCellID = vocabulary(cellID);
        Figure figure = null;
        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            if (entry.getKey().equals(sCellID)) figure = entry.getValue();
        }
        return figure;
    }

    public Figure getFigureOnCell(String cellID) {
        Figure figure = null;
        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            if (entry.getKey().equals(cellID)) figure = entry.getValue();
        }
        return figure;
    }

    public String getSide(Figure figure) {
        if (figure.getId().startsWith("black")) {
            return "black";
        } else if (figure.getId().startsWith("white")) {
            return "white";
        } else return "error";

    }

}
