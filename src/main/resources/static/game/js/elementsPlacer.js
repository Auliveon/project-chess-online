'use strict';
let s = null;

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
    gameBoard.id = 'gameField';
    // gameBoard.style.left = "200px";
    document.querySelector('#game').append(gameBoard);
    //document.body.append(gameBoard);
    console.log(gameBoard.getBoundingClientRect());
    return gameBoard;
}

function createCells(gameBoard) {
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify("getSide"),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp => resp.json())
        .then(resp => {
            s = JSON.parse(JSON.stringify(resp)).message;
        })
        .then(() => {

            let cellResolution = gameSettings.gameFieldSize / 8;
            let id = s === "white" ? 8 : 1;
            let ch = ["a", "b", "c", "d", "e", "f", "g", "h"];
            let chBrush = 0;
            let swapped = false;
            let x = 0;
            let y = 0;
            let color1 = s === 'white' ? '#f0d9b5' : '#b58863';
            let color2 = s === 'white' ?'#b58863' : '#f0d9b5';
            for (let i = 0; i < 64; i++) {

                if (i % 8 === 0 && i !== 0) {
                    y = 0;
                    x += 80;
                    id = s === "white" ? 8 : 1;
                    chBrush++;
                    if (!swapped) {
                        color1 = s === 'white' ?'#b58863' : '#f0d9b5';
                        color2 = s === 'white' ? '#f0d9b5' : '#b58863';
                        swapped = true;
                    } else {
                        color1 = s === 'white' ? '#f0d9b5' : '#b58863';
                        color2 = s === 'white' ?'#b58863' : '#f0d9b5';
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
                s === "white" ? id-- : id++;
            }

        });
}

    document.addEventListener("DOMContentLoaded", () => {
        start();

        function start() {
            let gameBoard = createBoard();
            createCells(gameBoard);
        }
    });