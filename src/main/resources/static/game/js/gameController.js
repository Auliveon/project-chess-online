let query = null;
function s() {
  let sInt = setInterval(function () {
       fetch("/ajax", {
           method: "POST",
           body: JSON.stringify(query),
           headers: {
               'Content-type': 'application/json'
           }
       }).then(resp => resp.text()).then(resp => {
           if (resp !== "no") {
               side = resp;
               addEventListenersOnFigures();
               console.log(resp);
               clearInterval(sInt);
           }
       });

   }, 2000)

}

let side = 'none';
let stepStartPosition = {
    startCell: null,
    figure: null
}
//====================================================================

function addEventListenersOnFigures() {
    figuresOnField.forEach((figure) => {
        figure.addEventListener('click', fc)
    })
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
    // elements.forEach((item) => {
    //     if (item.className === "cell") {
    //         elem = item;
    //     }
    // });
    return elements;
}


let fc = function (event) {
    let elementsUnderMouse = getElementByCords(event.target);
    console.log(elementsUnderMouse);
    elementsUnderMouse.forEach((el) => {
        if(el.id === 'cell') {
            stepStartPosition.startCell = el.id;
            let waiting = new Promise()
        }
    })

}


//===================================================================================================