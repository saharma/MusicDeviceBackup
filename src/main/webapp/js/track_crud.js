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
                url: "track/show",

                success: function (trackList) {
                    $.each(trackList, function (index, value) {
                        $('#trackHolder').append("<li>" + value.track_id + " " +
                            value.name + " " +
                            value.artist + " " +
                            value.album + " " +
                            value.genre + " " +
                            value.year + " " +
                            value.size + " " +
                            value.library_ID + "</li>");

                    });

                }});

        });


    });