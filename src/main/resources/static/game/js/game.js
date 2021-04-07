'use strict';
const gameSettings = {
    figuresMap: {
        whitePawn: ["a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"],
        blackPawn: ["a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"],
        blackRook: ["a8", "h8"],
        whiteRook: ["a1", "h1"],
        blackBishop: ["b8", "g8"],
        whiteBishop: ["b1", "g1"],
        blackKnight: ["c8", "f8"],
        whiteKnight: ["c1", "f1"],
        blackQueen: ["d8"],
        whiteQueen: ["d1"],
        blackKing: ["e8"],
        whiteKing: ["e1"]
    },
    assetsPath: "./game/assets/",
    figures: {
        blackPawn: "bP.png",
        whitePawn: "wP.png",
        blackBishop: "bB.png",
        whiteBishop: "wB.png",
        blackRook: "bR.png",
        whiteRook: "wR.png",
        blackKnight: "bN.png",
        whiteKnight: "wN.png",
        blackQueen: "bQ.png",
        whiteQueen: "wQ.png",
        blackKing: "bK.png",
        whiteKing: "wK.png"
    },
    borderValue: 4,
    gameFieldSize: 640,
    figureSizePart: 40
};
let figuresOnField = [];

function createBoard() {
    let gameBoard = document.createElement('div');

    gameBoard.style.position = "relative";
    gameBoard.style.width = (gameSettings.gameFieldSize + gameSettings.borderValue) + "px";
    gameBoard.style.height = (gameSettings.gameFieldSize + gameSettings.borderValue) + "px";
    gameBoard.style.background = null;
    gameBoard.style.border = `${gameSettings.borderValue / 2}px solid`;
    // gameBoard.style.left = "200px";

    document.body.append(gameBoard);
    console.log(gameBoard.getBoundingClientRect());
    return gameBoard;
}

function createCells(gameBoard) {
    let cellResolution = gameSettings.gameFieldSize / 8;
    let id = 8;
    let ch = ["a", "b", "c", "d", "e", "f", "g", "h"];
    let chBrush = 0;
    let swapped = false;
    let x = 0;
    let y = 0;
    let color1 = "#f0d9b5";
    let color2 = "#b58863";
    for (let i = 0; i < 64; i++) {

        if (i % 8 === 0 && i !== 0) {
            y = 0;
            x += 80;
            id = 8;
            chBrush++;
            if (!swapped) {
                color1 = "#b58863";
                color2 = "#f0d9b5";
                swapped = true;
            } else {
                color1 = "#f0d9b5";
                color2 = "#b58863";
                swapped = false;
            }
        }

        let cell = document.createElement('div');
            cell.style.background = i % 2 === 0 ? color1 : color2;
            cell.style.position = "absolute";
            cell.style.width = cellResolution + "px";
            cell.style.height = cellResolution + "px";
            cell.style.left = x + 'px';
            cell.style.top = y + 'px';
            cell.id = ch[chBrush] + id + '';
            cell.className = "cell";
            gameBoard.append(cell);
        y += 80;
        id--;
    }
}

function addFiguresOnField(arrayCell) {
    arrayCell.forEach((item) =>  {
        for(let key in gameSettings.figuresMap) {
            gameSettings.figuresMap[key].forEach((el) => {
                if(item.id === el) {
                    let img = document.createElement("img");
                    img.id = key;
                    img.src = gameSettings.assetsPath + gameSettings.figures[key];
                    figuresOnField.push(img);
                    item.append(img);
                }
            });
        }
    });
}

function addEventListenersOnFigures(copyFigures) {
    copyFigures.forEach((figure) => {
        if(figure.id.startsWith("white")) {

        figure.onmousedown = function (e) {

            let coords = getCoords(figure);

            let shiftX = e.pageX - coords.left;
            let shiftY = e.pageY - coords.top;

            figure.style.position = 'absolute';
            document.body.appendChild(figure);
            moveAt(e);

            figure.style.zIndex = 1000 + "";

            function moveAt(e) {
                figure.style.left = e.pageX - shiftX + 'px';
                figure.style.top = e.pageY - shiftY + 'px';
            }

            document.onmousemove = function (e) {
                moveAt(e);
            };

            figure.onmouseup = function () {
                let cell = getElementByCords(figure);
                console.log(cell);
                figure.zIndex = 0;
                figure.style.position = "";
                cell.append(figure);

                document.onmousemove = null;
                figure.onmouseup = null;
            };
        }

        figure.ondragstart = function () {
            return false;
        };
        }
        function getCoords(elem) {
            let box = elem.getBoundingClientRect();
            return {
                top: box.top + pageYOffset,
                left: box.left + pageXOffset
            };
        }
    });
}

function getElementByCords(figure) {
    let elem = null;
    let elements = document.elementsFromPoint(figure.getBoundingClientRect().x + gameSettings.figureSizePart, figure.getBoundingClientRect().y + gameSettings.figureSizePart);
    elements.forEach((item) => {
        if (item.className === "cell") {
            elem = item;
        }
    });
    return elem;
}

document.addEventListener("DOMContentLoaded", function (event) {
    let gameBoard = createBoard();
    createCells(gameBoard);

    gameBoard.addEventListener('click', (event) => {
        console.log(event.target.id);
    });

    let cells = document.querySelectorAll(".cell");
    addFiguresOnField(cells);
    console.log("Данные загружены");
    addEventListenersOnFigures(figuresOnField);
});
