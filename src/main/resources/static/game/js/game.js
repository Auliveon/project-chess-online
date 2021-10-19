"use strict"
let step = {
    startPosition: null,
    figure: null,
    endPosition: null
}
let answer = {
    lastStep: null,
    whoTurn: null,
    message: null,
    gameField: null,
    side: null,
    enemyName: null
}
let message = {};
let side = "";
let who = "";
let enemyNode = null;
let yourNode = null;

let b = setInterval(() => {
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify("getChatMessages"),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp => resp.json())
        .then(resp => {
            createMessageDiv(resp)});
    // clearInterval(a);
}, 1000);

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

            if (answer.message === "updateField") {
                console.log(answer.whoTurn);
                who = answer.whoTurn;
                console.log(answer)
                updateField();
            }

            if (answer.message === "startGame") {
                side = answer.side;
                who = answer.whoTurn;
                enemyNode = document.querySelector('#enemyName');
                yourNode = document.querySelector('#yourName');
                addFiguresOnField();
            }

            if (answer.message === "Lose") {
                alert("You lose");
                window.location.href = '/';
            }
            if (answer.message === "Win") {
                alert("You win");
                window.location.href = '/';

            }

        });
    // clearInterval(a);
}, 100);

function sendMessage(text) {
    fetch("/ajax", {
        method: "POST",
        body: 'addChatMessage' + String.fromCharCode(3798) + JSON.stringify({author: "", text: text}),
        headers: {
            'Content-type': 'application/json'
        }
    });
}

function addFiguresOnField() {
    enemyNode.textContent = answer.enemyName;
    if (answer.whoTurn === "You") {
        document.querySelector("#sword2").hidden = false;
        document.querySelector("#sword").hidden = true;
    } else {
        document.querySelector("#sword2").hidden = true;
        document.querySelector("#sword").hidden = false;
    }

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
                if (elem.classList.contains("cell")) {
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
    if (answer.whoTurn === "You") {
        document.querySelector("#sword2").hidden = false;
        document.querySelector("#sword").hidden = true;
    } else {
        document.querySelector("#sword2").hidden = true;
        document.querySelector("#sword").hidden = false;
    }
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
                    if (tempF != null) tempF.remove();
                    cell.append(f)
                }

            } else {
                let tempF = cell.querySelector("img");
                if (tempF != null) tempF.remove();

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

    if (who === "You" && answer.lastStep !== null) {
        let step = answer.lastStep.split("-");
        let startPosition = document.querySelector(`#${step[0]}`);
        let endPosition = document.querySelector(`#${step[1]}`);
        startPosition.classList.add("border", "border-3", "border-danger");
        endPosition.classList.add("border", "border-3", "border-danger");
    } else {
        document.querySelectorAll(`.cell`).forEach((elem) => {
            elem.classList.remove("border", "border-3", "border-danger");
        });
    }

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

function createMessageDiv(m) {
    $('#div1').html("");
    $.each(m, function () {
        $('#div1').append($('<div>').attr('name', 'messageDiv').addClass('row border border-2 rounded-2').addClass(this.author !== $('#enemyName').text() ? 'border-danger' : 'border-primary').css('margin-bottom', '1px').append($('<div>').addClass('col-12').append($('<label>').css({'margin': '15px'}).text(this.text))));
    });
    $('div[name=messageDiv]').width($('#div1').width() + 9);
}

document.addEventListener("DOMContentLoaded", () => {
    addMessageSender();
});

function addMessageSender() {
    $(window).resize(function() {
        $('#game2').offset({top: $('#game').offset().top, left: $('#game').offset().left + ($('#game').width())});
        $('#game2').css({'position': ''});
    })
    $('#game2').append($('<div>').attr('id', 'div1').height($('#game2').height() - 50).width($('#game2').width() + 12).css('background', 'white').css('overflow-x', 'hidden'));
    $('#game2').append($('<div>').attr('id', 'div2').addClass('col-12 input-group mb-3 mt-3')
        .append($('<input>', {
            type: 'text',
            name: 'sendMessageInput'
        }).prop({'placeholder': 'Введите сообщение'}).addClass('form-control'))
        .append($('<button>').addClass('btn btn btn-danger').attr({'name': 'sendMessageButton'}).prop({'type': 'button'}).text('Отправить').click(function () {
            sendMessage($('#div2').find('input').val());
            $('#div2').find('input').val("");
        })))
    $('#div2').offset({top: $('#div1').offset().top + $('#div1').height() + 1});
    $('#div2').height($('#div2').height() + 10);
    $('#div2').width($('#div1').width());
    $('#div2').find('input').width(140)
}




