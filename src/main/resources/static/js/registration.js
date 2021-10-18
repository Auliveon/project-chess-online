document.addEventListener('DOMContentLoaded', function () {

});
function sendActivationCode() {
    let regInfo = {};
    regInfo['login'] = $('#login').val();
    regInfo['password'] = $('#password').val();
    regInfo['passwordRepeat'] = $('#password_repeat').val();
    regInfo['email'] = $('#email').val();
    let ajax = $.ajax({
        type: 'POST',
        url: '/register/sendActivationCode/',
        contentType: 'application/json',
        data: JSON.stringify(regInfo),
        async: false
    });
    return ajax['responseJSON'];

}