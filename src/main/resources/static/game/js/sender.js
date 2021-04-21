//alert("123");
document.addEventListener("DOMContentLoaded", function (event) {
    createBtn();
    addEventListenerOnStartButton();
    checkInQueue();
    // checkStatus();
    startCheck();


});

function createBtn() {
    let startBtn = document.createElement('button');
    startBtn.classList.add("btn", "btn-dark");
    startBtn.id = "start";
    startBtn.textContent = "START";
    document.body.append(startBtn);

}


function sendReady(event) {
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify("ready"),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp => resp.text()).then((resp) => {
        console.log(resp);

    }).then(() => {
        event.target.textContent = "READY";
        event.target.removeEventListener('click', sendReady);
    });

}

function addEventListenerOnStartButton() {
    let startBtn = document.querySelector('#start');
    startBtn.addEventListener('click', sendReady);
}

function checkInQueue() {
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify("checkMeOnQueue"),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp => resp.text()).then(resp => {
        if (resp === "yes") {
            let startBtn = document.querySelector('#start');
            startBtn.textContent = "READY";
            startBtn.removeEventListener('click', sendReady);

        }
    });
}

function startCheck() {
    let interval = setInterval(() => {
        fetch("/ajax", {
            method: "POST",
            body: JSON.stringify("checkGame"),
            headers: {
                'Content-type': 'application/json'
            }
        }).then(resp => resp.text()).then(resp => {
            if (resp !== "no") {
                start();
                s();
                clearInterval(interval);
            }
        });

    }, 2000);
}



