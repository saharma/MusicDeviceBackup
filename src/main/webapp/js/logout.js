/**
 * Created by saharmohamedali on 08/05/2017.
 */

$(document).ready(function() {
    $(function() {
        $(".logoutButton").on("click",function(e) {
            // e.preventDefault(); // cancel the link itself
            $.post('/mdbu-0.0.1-SNAPSHOT/UserSessionServlet');
        });
    });

});