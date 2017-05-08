/**
 * Created by saharmohamedali on 08/05/2017.
 */
var User = function(username, password, library_persistent_id) {
    this.username = username;
    this.password = password;
    this.library_persistent_id = library_persistent_id;
}

$(document).ready(function()
{

    $("#reg").click(function()
    {


        var username =  $("#setUsername").val();
        var password = $("#setUserPassword").val();
        var library_persistent_id = "69A7F174F7AEE335";


        if(username && password){
            document.getElementById('alert2').style.display = 'none';
            var myUser = new User(username, password, library_persistent_id );


            $.ajax({
                type: "POST",
                url: "api/user",
                success: function () {
                    document.getElementById('alert2').innerHTML = 'User Added';
                    document.getElementById('alert2').style.display = 'block';
                },
                error: function(){
                    document.getElementById('alert2').innerHTML = '<strong>Warning!</strong> User Registration Failure';
                    document.getElementById('alert2').style.display = 'block';
                },
                data: JSON.stringify(myUser),
                contentType: "application/json",
            });
        }
        else{
            document.getElementById('alert2').innerHTML = '<strong>Warning!</strong> Please enter username and password';
            document.getElementById('alert2').style.display = 'block';
        }


    });

    $("#showUserList").click(function() {
        alert("test");
        $.ajax({

            type: "GET",
            url: "ragnar/user",
            success: function (userList) {
                $.each(userList, function (index, value) {
                    $("#userHolder").append("<li>" + value.username + " " + value.password  + value.library_persistent_id  +"</li>");
                });
            }});
    });

    $("#closeUserList").click(function() {
        $("#userHolder").empty();
    });


});