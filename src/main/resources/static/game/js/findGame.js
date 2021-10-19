function findGame() {
    fetch("/ajax", {
        method: "POST",
        body: JSON.stringify("addMeOnQueue"),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(resp => resp.text()).then((resp) => {
        console.log(resp);
        if (resp === "added") {
            fetch("/getmodal", {
                method: "POST",
                body: JSON.stringify("addMeOnQueue"),
                headers: {
                    'Content-type': 'application/json'
                }
            }).then(resp => resp.text()).then(fragment => {
                document.querySelector("#findingGame").innerHTML = fragment;
            }).then(() => {
                let model = new bootstrap.Modal(document.getElementById('findingGame'), {});
                model.show();
                checkStatus();
                return resp;
            });

        }
    });
}

function removeFromQueue() {
    fetch("/ajax", {
        method: "POST",
        body: 'removeMeFromQueue',
        headers: {
            'Content-type': 'application/json'
        }
    });
    window.location.href = '/findgame';


}

function checkStatus(stop) {
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
                if (answer.message === "gameReady") {
                    clearInterval(a);
                    window.location.href = "/game";

                }
            });
    }, 1000);
}


document.addEventListener("DOMContentLoaded", () => {
});