/**
 * Created by saharmohamedali on 07/05/2017.
 */


jQuery('document').ready(function() {
    $("#loginChoiceButton").click(function () {
        window.location.href = 'login.html';
    });

    $("#RegChoiceButton").click(function () {
        window.location.href = 'register.html';
    });
    $(".goToIndex").click(function () {
        window.location.href = 'index.html';
    });

});