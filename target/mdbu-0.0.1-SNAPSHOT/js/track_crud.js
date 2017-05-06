/**
 * Created by saharmohamedali on 06/05/2017.
 */

var track = function(track_id, name, artist, album, genre, year, size, library_ID) {
this.track_id = track_id;
this.name = name;
this.artist = artist;
this.album = album;
this.genre = genre;
this.year = year;
this.size = size;
this.library_ID = library_ID;

}


//SHOW TRACKS - FUNCTION IN LIBRARY.HTML

    $(document).ready(function () {

        $("#showTrackButton").click(function () {
            $.ajax({
                type: "GET",
                url: "api/track/getAllTracks",

                success: function (trackList) {
                    $.each(trackList, function (index, value) {
                        $('#trackHolder').append("<tr>" +
                            "<td>" + value.track_id + "</td>" +
                            "<td>" + value.name + "</td>" +
                            "<td>" + value.artist + "</td>" +
                            "<td>" + value.album + "</td>" +
                            "<td>" + value.genre + "</td>" +
                            "<td>" + value.year + "</td>" +
                            "<td>" + value.size + "</td>" +
                            "<td>" + value.library_ID + "</td>"
                        + "</tr>"
                        );

                    });

                }});

        });


    });