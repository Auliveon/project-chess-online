package com.ChessOnline.game;

import com.ChessOnline.game.elements.*;

import java.util.*;

public class GameEngine {
    private Map<String, Figure> gameField = new HashMap<>();

    public GameEngine() {

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

    public String checkMat() {
        Set<String> whiteSteps = new HashSet<>();
        Set<String> blackSteps = new HashSet<>();
        for (Map.Entry<String, Figure> stringFigureEntry : gameField.entrySet()) {
            if (stringFigureEntry.getValue() != null && stringFigureEntry.getValue().getId().startsWith("white")

            ) {
                whiteSteps.addAll(getAvailableSteps(stringFigureEntry.getValue().getId()));
            } else if (stringFigureEntry.getValue() != null && stringFigureEntry.getValue().getId().startsWith("black")

            ) {
                blackSteps.addAll(getAvailableSteps(stringFigureEntry.getValue().getId()));
            }
        }
        System.out.println(whiteSteps);
        System.out.println(blackSteps);
        if (whiteSteps.size() == 0 && blackSteps.size() > 0 && checkShah("white", gameField)) return "black";
        else if (blackSteps.size() == 0 && whiteSteps.size() > 0 && checkShah("black", gameField)) return "white";
        else if((whiteSteps.size() == 0 && blackSteps.size() > 0 || blackSteps.size() == 0 && whiteSteps.size() > 0)) return "pat";
        return "none";
    }

    public String makeTurn(String step) {

        String convertedStep = step.substring(1, step.length() - 1);
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

        ArrayList<String> availableSteps = getAvailableSteps(sFigure);
        if (availableSteps.contains(array[3]) && sFigure.startsWith("whiteKing") && array[3].equals("a1")) {
            assert figure != null;
            figure.setStepsCount(figure.getStepsCount() + 1);
            gameField.put("c1", figure);
            gameField.put("d1", getFigureById("whiteRook1", gameField));
            gameField.put(cell, null);
            gameField.put("a1", null);

            return "Yes";
        } else if (availableSteps.contains(array[3]) && sFigure.startsWith("whiteKing") && array[3].equals("h1")) {
            assert figure != null;
            figure.setStepsCount(figure.getStepsCount() + 1);
            gameField.put("g1", figure);
            gameField.put("f1", getFigureById("whiteRook2", gameField));
            gameField.put(cell, null);
            gameField.put("h1", null);

            return "Yes";
        } else if (availableSteps.contains(array[3]) && sFigure.startsWith("blackKing") && array[3].equals("h8")) {
            assert figure != null;
            figure.setStepsCount(figure.getStepsCount() + 1);
            gameField.put("g8", figure);
            gameField.put("f8", getFigureById("blackRook2", gameField));
            gameField.put(cell, null);
            gameField.put("h8", null);

            return "Yes";
        } else if (availableSteps.contains(array[3]) && sFigure.startsWith("blackKing") && array[3].equals("a8")) {
            assert figure != null;
            figure.setStepsCount(figure.getStepsCount() + 1);
            gameField.put("c8", figure);
            gameField.put("d8", getFigureById("blackRook1", gameField));
            gameField.put(cell, null);
            gameField.put("a8", null);

            return "Yes";
        } else if (availableSteps.contains(array[3])) {
            assert figure != null;
            figure.setStepsCount(figure.getStepsCount() + 1);
            gameField.put(array[3], figure);
            gameField.put(cell, null);
            System.out.println("Ok");

            return "Yes";
        } else {
            System.out.println("Cannot make step!");
            return "No";
        }
    }

    public ArrayList<String> getAvailableSteps(String figureId) {

        Figure figure = getFigureById(figureId, gameField);
        ArrayList<String> steps = new ArrayList<>();

        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {

            if (entry.getValue() != null && entry.getValue().getId().equals(figureId)) {

                Map<String, Figure> tempGameField = copyGameField();
                ArrayList<String> tempSteps = getSteps(figureId, true, true, gameField);

                for (String elem : tempSteps) {
                    if (tempGameField.get(elem) != null && tempGameField.get(elem).getId().equals("whiteRook1") && figure.getId().startsWith("white")) {
                        tempGameField.put("d1", getFigureById("whiteRook1", tempGameField));
                        tempGameField.put(elem, null);
                        tempGameField.put("c1", getFigureById("whiteKing1", tempGameField));
                        tempGameField.put("e1", null);
                    } else if (tempGameField.get(elem) != null && tempGameField.get(elem).getId().equals("whiteRook2") && figure.getId().startsWith("white")) {
                        tempGameField.put("f1", getFigureById("whiteRook2", tempGameField));
                        tempGameField.put(elem, null);
                        tempGameField.put("g1", getFigureById("whiteKing1", tempGameField));
                        tempGameField.put("e1", null);
                    } else {

                        tempGameField.put(elem, figure);
                        tempGameField.put(entry.getKey(), null);
                        //System.out.println(elem + tempGameField);
                    }
                    if (!checkShah(figure, tempGameField)) {
                        steps.add(elem);
                        //System.out.println("not shahed");
                    }
                    tempGameField = copyGameField();
                }
            }
        }
        return steps;
    }

    public ArrayList<String> getSteps(String f, boolean forPawn, boolean forKing, Map<String, Figure> gameField) {

        Figure figure = null;

        String sFigure = f;

        String currentCell = null;

        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getId().equals(sFigure)) {
                currentCell = entry.getKey();
                figure = entry.getValue();
            }
        }


        int iCell = vocabulary(currentCell);

        ArrayList<String> cells = new ArrayList<>();
        if (!(figure instanceof WhitePawn) && !(figure instanceof BlackPawn) && !(figure instanceof Knight)) {


            for (int element : figure.getStepValue()) {
                int tempICell = iCell;
                int varCount = figure.getVarCount();

                while (true) {
                    if (varCount == 0) {
                        break;
                    }
                    String tempValue = vocabulary(tempICell + element);

                    if (!tempValue.equals("wrong cell")) {
                        if (getFigureOnCell(vocabulary(tempValue), gameField) != null && getSide(getFigureOnCell(vocabulary(tempValue), gameField)).equals(getSide(figure))) {
                            break;
                        } else if (getFigureOnCell(vocabulary(tempValue), gameField) != null && !getSide(getFigureOnCell(vocabulary(tempValue), gameField)).equals(getSide(figure))) {
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
            if (forPawn) {

                for (int element : figure.getStepValue()) {
                    int tempICell = iCell;

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
                            if (getFigureOnCell(vocabulary(tempValue), gameField) != null) {
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
            }

            if (figure instanceof WhitePawn) {
                if (!(vocabulary(iCell + (-9)).equals("wrong cell")) && getFigureOnCell(iCell + (-9), gameField) != null
                        && !getSide(figure).equals(getSide(getFigureOnCell(iCell + (-9), gameField)))) {
                    cells.add(vocabulary(iCell + (-9)));
                }
                if (!(vocabulary(iCell + (11)).equals("wrong cell")) && getFigureOnCell(iCell + (11), gameField) != null
                        && !getSide(figure).equals(getSide(getFigureOnCell(iCell + (11), gameField)))) {
                    cells.add(vocabulary(iCell + (11)));
                }

            }

            if (figure instanceof BlackPawn) {
                if (!(vocabulary(iCell + (-11)).equals("wrong cell")) && getFigureOnCell(iCell + (-11), gameField) != null
                        && !getSide(figure).equals(getSide(getFigureOnCell(iCell + (-11), gameField)))) {
                    cells.add(vocabulary(iCell + (-11)));
                }
                if (!(vocabulary(iCell + (9)).equals("wrong cell")) && getFigureOnCell(iCell + (9), gameField) != null
                        && !getSide(figure).equals(getSide(getFigureOnCell(iCell + (9), gameField)))) {
                    cells.add(vocabulary(iCell + (9)));
                }

            }


        } else if (figure instanceof Knight) {
            for (int element : figure.getStepValue()) {
                int tempCell = iCell;
                int varCount = figure.getVarCount();

                while (true) {
                    String tempValue = vocabulary(tempCell + element);
                    if (varCount <= 0) {
                        break;
                    }
                    if (!(vocabulary(element + tempCell).equals("wrong cell"))) {
                        if (getFigureOnCell(vocabulary(tempValue), gameField) != null && getSide(getFigureOnCell(vocabulary(tempValue), gameField)).equals(getSide(figure))) {
                            break;
                        } else if (getFigureOnCell(vocabulary(tempValue), gameField) != null && !getSide(getFigureOnCell(vocabulary(tempValue), gameField)).equals(getSide(figure))) {
                            cells.add(tempValue);
                            break;
                        } else if (getFigureOnCell(vocabulary(tempValue), gameField) == null) {
                            cells.add(tempValue);
                            break;
                        }
                    } else {
                        break;
                    }

                }
            }
        }
        if (figure instanceof King) {

            if (forKing) {

                if (figure.getId().startsWith("white")) {
                    boolean usedS = false;
                    boolean usedL = false;
                    System.out.println(usedL);
                    for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
                        if (entry.getKey().equals("b1") || entry.getKey().equals("c1") || entry.getKey().equals("d1") || entry.getKey().equals("e1")) {
                            if (entry.getValue() != null) {
                                usedL = true;
                            }
                        }
                    }
                    for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
                        if (entry.getKey().equals("f1") || entry.getKey().equals("g1")) {
                            if (entry.getValue() != null) {
                                usedS = true;
                            }
                        }
                    }
                    if (!usedL && getFigureById("whiteRook1", gameField).getStepsCount() == 0 && figure.getStepsCount() == 0) {
                        Set<String> strings = getAttackedPos("black", gameField);
                        if (strings.contains("b1") || strings.contains("c1") || strings.contains("d1") || strings.contains("e1")) {
                            usedL = true;
                        }
                    } else {
                        usedL = true;
                    }
                    if (!usedS && getFigureById("whiteRook2", gameField).getStepsCount() == 0 && figure.getStepsCount() == 0) {
                        Set<String> strings = getAttackedPos("black", gameField);
                        if (strings.contains("f1") || strings.contains("g1") || strings.contains("e1")) {
                            usedS = true;
                        }
                    } else {
                        usedS = true;
                    }
                    if (!usedL) cells.add("a1");
                    if (!usedS) {
                        cells.add("h1");
                        System.out.println("usedS");
                    }
                } else {
                    boolean usedS = false;
                    boolean usedL = false;
                    System.out.println(usedL);
                    for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
                        if (entry.getKey().equals("b8") || entry.getKey().equals("c8") || entry.getKey().equals("d8") || entry.getKey().equals("e8")) {
                            if (entry.getValue() != null) {
                                usedL = true;
                            }
                        }
                    }
                    for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
                        if (entry.getKey().equals("f8") || entry.getKey().equals("g8")) {
                            if (entry.getValue() != null) {
                                usedS = true;
                            }
                        }
                    }
                    if (!usedL && getFigureById("blackRook1", gameField).getStepsCount() == 0 && figure.getStepsCount() == 0) {
                        Set<String> strings = getAttackedPos("white", gameField);
                        if (strings.contains("b8") || strings.contains("c8") || strings.contains("d8") || strings.contains("e8") ) {
                            usedL = true;
                        }
                    } else {
                        usedL = true;
                    }
                    if (!usedS && getFigureById("blackRook2", gameField).getStepsCount() == 0 && figure.getStepsCount() == 0) {
                        Set<String> strings = getAttackedPos("white", gameField);
                        if (strings.contains("f8") || strings.contains("g8") || strings.contains("e8")) {
                            usedS = true;
                        }
                    } else {
                        usedS = true;
                    }
                    if (!usedL) cells.add("a8");
                    if (!usedS) {
                        cells.add("h8");
                        System.out.println("usedS");
                    }
                }


            }
        }

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

    public Figure getFigureOnCell(int cellID, Map<String, Figure> gameField) {

        String sCellID = vocabulary(cellID);
        Figure figure = null;
        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            if (entry.getKey().equals(sCellID)) figure = entry.getValue();
        }
        return figure;
    }

    public Figure getFigureOnCell(String cellID, Map<String, Figure> gameField) {

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

    public boolean checkShah(Figure figure, Map<String, Figure> gameField1) {

        String side = getSide(figure);

        String cell = null;

        for (Map.Entry<String, Figure> entry : gameField1.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getId().startsWith(side) && entry.getValue() instanceof King) {
                cell = entry.getKey();
            }
        }

        Set<String> strings = new HashSet<>();
        for (Map.Entry<String, Figure> entry : gameField1.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().getId().startsWith(side)) {
                strings.addAll(getSteps(entry.getValue().getId(), true, false, gameField1));
            }
        }
        return strings.contains(cell);

    }

    public boolean checkShah(String side, Map<String, Figure> gameField1) {

        String cell = null;

        for (Map.Entry<String, Figure> entry : gameField1.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getId().startsWith(side) && entry.getValue() instanceof King) {
                cell = entry.getKey();
            }
        }

        Set<String> strings = new HashSet<>();
        for (Map.Entry<String, Figure> entry : gameField1.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().getId().startsWith(side)) {
                strings.addAll(getSteps(entry.getValue().getId(), true, false, gameField1));
            }
        }
        System.out.println(side + " " + cell + " " + strings);
        return strings.contains(cell);

    }

    public Figure getFigureById(String sFigure, Map<String, Figure> gameField) {

        Figure figure = null;
        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getId().equals(sFigure)) {
                figure = entry.getValue();
            }
        }
        return figure;
    }

    public Map<String, Figure> copyGameField() {
        Map<String, Figure> copyMap = new HashMap<>();
        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            copyMap.put(entry.getKey(), entry.getValue());
        }
        return copyMap;
    }

    public Set<String> getAttackedPos(String side, Map<String, Figure> gameField) {
        Set<String> strings = new HashSet<>();
        for (Map.Entry<String, Figure> entry : gameField.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getId().startsWith(side)) {
                strings.addAll(getSteps(entry.getValue().getId(), false, false, gameField));
            }
        }
        return strings;
    }
}
