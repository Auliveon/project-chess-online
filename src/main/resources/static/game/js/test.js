"use strict"
let step = {
    startPosition: null,
    figure: null,
    endPosition: null
}

let side = "black";

let a = setInterval(() => {
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify("getGameField"),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp => resp.text())
        .then(resp => {

                addFiguresOnField()


            if(resp === "black" || resp === "white") {
                side = resp;
            }

        })
    clearInterval(a);
}, 2000);


function addFiguresOnField() {
    let gameFieldMap = {};
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify("getGameField"),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp => {
        return resp.json()
    }).then((resp) => {
        gameFieldMap = JSON.parse(JSON.stringify(resp));
        //console.log(gameFieldMap);
    }).then(() => {
        gameFieldMap = JSON.parse(JSON.stringify(gameFieldMap["gameField"]));
        for (let figure in gameFieldMap) {
            if (gameFieldMap[figure] != null) {
                let cell = document.querySelector(`#${figure}`);
                let f = document.createElement("img");
                let figureName = gameFieldMap[figure].id.substring(0, gameFieldMap[figure].id.length - 1);
                f.src = gameSettings.assetsPath + gameSettings.figures[figureName];
                f.id = gameFieldMap[figure].id;
                f.className = figureName;
                cell.append(f);
            }
        }
    }).then(() => {
        addEventsOnFigures();
    }).then(() => {
        let contFriendlyFigure = false;
        let cells = document.querySelectorAll(".cell");
        cells.forEach((cell) => {
            cell.addEventListener('click',f);
        })

    }).then(() => {


    });
}

function addEventsOnFigures() {
    let figures = document.querySelectorAll("img");
    figures.forEach(fig => {
        if(fig.id.startsWith(side)) {
            fig.addEventListener('click', figuresEvent);
        }
    });
}

function figuresEvent(event) {

    let elements = document.elementsFromPoint(event.target.getBoundingClientRect().x + gameSettings.figureSizePart,
        event.target.getBoundingClientRect().y + gameSettings.figureSizePart);
    step.startPosition = null;
    step.figure = null;
    step.endPosition = null;
    elements.forEach(elem => {
        if (elem.className === "cell") {
            step.startPosition = elem.id;
        }
    });
    step.figure = event.target.id;
    console.log(step);

}

 function f(event) {
    let contFriendlyFigure = false;
                    if (step.startPosition != null && step.figure != null) {
                        let elements = document.elementsFromPoint(event.target.getBoundingClientRect().x + gameSettings.figureSizePart,
                            event.target.getBoundingClientRect().y + gameSettings.figureSizePart);
                        elements.forEach((e) => {
                            if (e.id.startsWith(side)) {
                                contFriendlyFigure = true;
                            }
                        });
                        if(!contFriendlyFigure) {
                            step.endPosition = event.target.id;
                            console.log(step);
                            step.startPosition = null;
                            step.figure = null;
                            step.endPosition = null;
                            removeAllEventsFromFigures();
                        }
                    }

}
function removeAllEventsFromFigures() {
    let figures = document.querySelectorAll("img");
    figures.forEach(figur => {
        figur.removeEventListener('click', figuresEvent);
    })

}

