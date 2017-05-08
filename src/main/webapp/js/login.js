/**
 * Created by saharmohamedali on 08/05/2017.
 */

$(document).ready(function() {
        checkPermissions();
    });

function checkPermission() {
    $.get('/mdbu-0.0.1-SNAPSHOT/UserSessionServlet', function (data) {
        var user = JSON.parse(data);
        var library_persistent_id = 0;
        var welcome = document.getElementById('welcomeMsg');
        if (user != null) {
            library_persistent_id = user.library_persistent_id;
            welcome.innerHTML = "Welcome " + user.username;
        }
        else {
            welcome.innerHTML = "Please Login";
        }
        permissions(library_persistent_id);

    });
}