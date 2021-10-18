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
        async: false
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
            window.location.href = "/"
        },
        error: function () {
            event.preventDefault();
            $('#errorDiv').removeAttr('hidden');
        }});
    return ajax['responseJSON'];

}