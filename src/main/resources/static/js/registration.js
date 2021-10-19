document.addEventListener('DOMContentLoaded', function () {

});
function sendActivationCode() {
    let regInfo = {};
    regInfo['login'] = $('#login').val();
    regInfo['password'] = $('#password').val();
    regInfo['passwordRepeat'] = $('#password_repeat').val();
    regInfo['email'] = $('#email').val();
    regInfo['activationCode'] = $('#activationCode').val();

    let ajax = $.ajax({
        type: 'POST',
        url: '/register/sendActivationCode',
        contentType: 'application/json',
        data: JSON.stringify(regInfo),
        async: false,
        success: function () {
            $('#completeReg').prop({'disabled': ''});
            $('#activationCode').prop({'disabled': ''});
        },
        error: function () {
            event.preventDefault();
            $('#errorDiv').removeAttr('hidden');
        }
    });
    return ajax['responseJSON'];

}

function completeRegistration(event) {
    let regInfo = {};
    regInfo['login'] = $('#login').val();
    regInfo['password'] = $('#password').val();
    regInfo['passwordRepeat'] = $('#password_repeat').val();
    regInfo['email'] = $('#email').val();
    regInfo['activationCode'] = $('#activationCode').val();
    let ajax = $.ajax({
        type: 'POST',
        url: '/register/completeRegister',
        contentType: 'application/json',
        data: JSON.stringify(regInfo),
        async: false,
        success: function () {
            event.preventDefault();
            let formData = new FormData();
            formData.append('username', $('#login').val());
            formData.append('password', $('#password').val());
            let completeReg = $.ajax({
                    type: 'POST',
                    url: '/login',
                    processData: false,
                    contentType: false,
                    data: formData,
                    async: false,
            success: function () {
                        alert(23);
                window.location.href = "/";
            }});
            },
        error: function () {
            event.preventDefault();
            $('#errorDiv').removeAttr('hidden');
        }});
    return ajax['responseJSON'];

}