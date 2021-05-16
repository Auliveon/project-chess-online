"use strict"
let step = {
    startPosition: null,
    figure: null,
    endPosition: null
}
let answer = {
    type: null,
    whoTurn: null,
    message: null,
    gameField: null,
    side: null
}
let side = "";
let who = "";

fetch("/ajax", {
    method: "POST",
    body: JSON.stringify("addMeOnQueue"),
    headers: {
        'Content-type': 'application/json'
    }
}).then(resp => resp.text()).then((resp) => {
    console.log(resp);
})

let a = setInterval(() => {
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify("status"),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp => resp.json())
        .then(resp => {

            answer = JSON.parse(JSON.stringify(resp));
        })
        .then(() => {
            //console.log(answer);
            if (answer.message === "updateField") {
                console.log(answer.whoTurn);
                who = answer.whoTurn;
                updateField();
            }

            if (answer.message === "startGame") {
                side = answer.side;
                who = answer.whoTurn;
                addFiguresOnField();
            }

        });
    // clearInterval(a);
}, 100);

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
        return gameFieldMap;
        //console.log(gameFieldMap);
    }).then((gameFieldMap) => {
        //console.log(gameFieldMap);
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
        if (who === "You")
            addEventsOnFigures();
    }).then(() => {
        let contFriendlyFigure = false;
        let cells = document.querySelectorAll(".cell");
        cells.forEach((cell) => {
            cell.addEventListener('click', f);
        })
    });
}

function addEventsOnFigures() {
    let figures = document.querySelectorAll("img");
    figures.forEach(fig => {
        if (fig.id.startsWith(side)) {
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
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify(`getAvailableSteps-${step.figure}`),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp =>
        resp.text()
    ).then(resp => {
        console.log(resp);
        let availableCells = resp.split("-");
        console.log(availableCells);
        availableCells.forEach(elem => {
            if (elem !== "") {
                console.log(elem);
                let cell = document.querySelector(`#${elem}`);
                if (cell.style.background === "rgb(181, 136, 99)") {
                    cell.style.background = "rgb(7, 134, 184)";
                } else if (cell.style.background === "rgb(240, 217, 181)") {
                    cell.style.background = "rgb(20, 167, 225)";
                }
            }
        })
        remove(step.figure);
    })

}

function f(event) {
    let contFriendlyFigure = false;
    if (step.startPosition != null && step.figure != null) {
        let elements = document.elementsFromPoint(event.target.getBoundingClientRect().x + gameSettings.figureSizePart,
            event.target.getBoundingClientRect().y + gameSettings.figureSizePart);
        console.log("sdsdsd" + event.target.id);
        elements.forEach((e) => {

            if (step.figure === event.target.id) {
                contFriendlyFigure = true;
            }
        });
        if (!contFriendlyFigure) {
            getElementByCords(event.target).forEach(elem => {
                if (elem.className === "cell") {
                    step.endPosition = elem.id;
                }
            })

            sendStep();

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

function sendStep() {
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify(`step-${step.startPosition}-${step.figure}-${step.endPosition}`),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(() => {
        console.log('send');
    })

}

function updateField() {
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
        return gameFieldMap;
        //console.log(gameFieldMap);
    }).then((gameFieldMap) => {
        //console.log(gameFieldMap);
        gameFieldMap = JSON.parse(JSON.stringify(gameFieldMap["gameField"]));
        for (let figure in gameFieldMap) {
            let found = false;
            let id = null;
            let cell = document.querySelector(`#${figure}`);
            let nodes = getElementByCords(cell);
            if (gameFieldMap[figure] != null) {
                nodes.forEach(elem => {
                    if (elem.id === gameFieldMap[figure].id) {
                        found = true;
                    }
                });
                if (found === false) {
                    let f = document.createElement("img");
                    let figureName = gameFieldMap[figure].id.substring(0, gameFieldMap[figure].id.length - 1);
                    f.src = gameSettings.assetsPath + gameSettings.figures[figureName];
                    f.id = gameFieldMap[figure].id;
                    f.className = figureName;
                    let tempF = cell.querySelector("img");
                    if(tempF != null) tempF.remove();
                    cell.append(f)
                }

            } else {
                let tempF = cell.querySelector("img");
                if(tempF != null) tempF.remove();

            }


        }
    }).then(() => {
        if (who === "You") {
            addEventsOnFigures();
            returnStartColors();
        } else {
            returnStartColors();
        }

    }).then(() => {
        let contFriendlyFigure = false;
        let cells = document.querySelectorAll(".cell");
        cells.forEach((cell) => {
            cell.addEventListener('click', f);
        });
    });
}

function getCoords(elem) {
    let box = elem.getBoundingClientRect();
    return {
        top: box.top + pageYOffset,
        left: box.left + pageXOffset
    };
}

function getElementByCords(figure) {
    let elem = null;
    let elements = document.elementsFromPoint(figure.getBoundingClientRect().x + gameSettings.figureSizePart,
        figure.getBoundingClientRect().y + gameSettings.figureSizePart);
    return elements;
}

function remove(figureID) {
    removeAllEventsFromFigures();
    let figure = document.querySelector(`#${figureID}`);
    figure.addEventListener('click', tempEvent)

}

function tempEvent() {
    step.startPosition = null;
    step.figure = null;
    addEventsOnFigures();
    let cellsArray = document.querySelectorAll(".cell");
    returnStartColors()
    document.querySelectorAll("img").forEach(elem => {
        elem.removeEventListener('click', tempEvent);
    })

}

function returnStartColors() {
    let cellsArray = document.querySelectorAll(".cell");
    cellsArray.forEach(elem => {
        if (elem.style.background === 'rgb(7, 134, 184)') elem.style.background = "#b58863";
        else if (elem.style.background === "rgb(20, 167, 225)") elem.style.background = "#f0d9b5";
    })
}