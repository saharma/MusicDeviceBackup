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
    $(".goToHome").click(function () {
        window.location.href = 'index.html';
    });
    $(".goToIndex").click(function () {
        window.location.href = 'index.html';
    });
    $(".viewTracks").click(function () {
        window.location.href = 'trackLibrary.html';
    });
    $(".viewPlaylists").click(function () {
        window.location.href = 'playlistLibrary.html';
    });
    $(".uploadFile").click(function () {
        window.location.href = 'upload.html';
    });
    $("#loginFormButton").click(function () {
        window.location.href = "upload.html";
    });
    $("#goToAddTrackButton").click(function () {
        window.location.href = "userOptions.html";
    });







});