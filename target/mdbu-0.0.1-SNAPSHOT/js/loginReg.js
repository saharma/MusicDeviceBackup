/**
 * Created by saharmohamedali on 07/05/2017.
 */


jQuery('document').ready(function() {
    checkPermission();
    $("#loginChoiceButton").click(function () {
        window.location.href = 'login.html';
    });

    $("#RegChoiceButton").click(function () {
        window.location.href = 'register.html';
    });
    $(".goToHome").click(function () {
        window.location.href = 'home.html';
    });
    $(".goToProfile").click(function () {
        window.location.href = 'index.html';
    });



    function checkPermission() {
        $.get('/mdbu-0.0.1-SNAPSHOT/UserSessionServlet', function (data) {
            var user = JSON.parse(data);
            var accessLevel = 0;
            var welcome = document.getElementById('welcomeMsg');
            if (user != null) {
                accessLevel = parseInt(user.library_persistent_id);
                welcome.innerHTML = "Welcome " + user.username;
            }
            else {
                welcome.innerHTML = "Please Login";
            }
            permissions(accessLevel);

        });
    }

});