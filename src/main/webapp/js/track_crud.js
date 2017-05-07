/**
 * Created by saharmohamedali on 06/05/2017.
 */

var track = function(track_id, name, artist, album, genre, library_ID) {
this.track_id = track_id;
this.name = name;
this.artist = artist;
this.album = album;
this.genre = genre;
this.library_ID = library_ID;

}

var countColumn = 0;
jQuery('document').ready(function() {

//SHOW TRACKS - FUNCTION IN LIBRARY.HTML
    $("#showTrackButton").click(function () {
        $.ajax({
            type: "GET",
            url: "api/track/getAllTracks/",

            success: function (trackList) {
                $.each(trackList, function (index, value) {

                    countColumn = countColumn + 1;

                    $('#trackHolder').append("<tr>" +
                        "<td>" + countColumn + "</td>" +
                        "<td>" + value.track_id + "</td>" +
                        "<td contenteditable>" + value.name + "</td>" +
                        "<td contenteditable>" + value.artist + "</td>" +
                        "<td contenteditable>" + value.album + "</td>" +
                        "<td contenteditable>" + value.genre + "</td>" +
                        + "</tr>"
                    );


                });

                $('#trackTable').DataTable({
                    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
                    iDisplayLength: 10,

                });

            }
        });


        $("#trackData").show();
        $("#hideTracksButton").show();

    });

    $("#addTrackButton").click(function () {
        alert("Add button pressed");

        var track_id = $("#Track_ID").val();
        var name = $("#Name").val();
        var artist = $("#Artist").val();
        var album = $("#Album").val();
        var genre = $("#Genre").val();
        var library_ID = "69A7F174F7AEE335";
        var myTrack = new track(track_id, name, artist, album, genre, library_ID);
        alert("Track ID: " + track_id + "Name: " + name + "Artist: " + artist +
            "Album: " + album + "Genre: " + genre);


        $.ajax({
            type: "POST",
            url: "api/track/",
            success: function () {
                alert("Track Added");
                location.reload();
            },
            error: function () {
                alert("Track Add failure")
            },
            data: JSON.stringify(myTrack),
            contentType: "application/json"
        });

    });


$("#hideTracksButton").click(function() {
    $("#trackData").hide();
    $("#hideTracksButton").hide();
});


});