document.addEventListener('DOMContentLoaded', function () {

})

function blockUser(event) {
    event.preventDefault();
        $.ajax({
            url: event.target.href,
            success: function (data) {
                $('#tableDiv').remove();
                $('header').after(data);
            }
        });
}

function unBlockUser(event) {
    event.preventDefault();
    $.ajax({
        url: event.target.href,
        success: function (data) {
            $('#tableDiv').remove();
            $('header').after(data);
        }
    });
}

function removeUser(event) {
    event.preventDefault();
    $.ajax({
        url: event.target.href,
        success: function (data) {
            $('#tableDiv').remove();
            $('header').after(data);
        }
    });
}