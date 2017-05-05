package mdbu.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by saharmohamedali on 30/04/2017.
 */

@Entity
@Table(name="track")
public class track implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="Track_ID") private Integer track_id;
    @Column(name="Name") private String name;
    @Column(name="Artist") private String artist;
    @Column(name="Album") private String album;
    @Column(name="Genre") private String genre;
    @Column(name="Year") private Integer year;
    @Column(name="Size") private Integer size;
    //Playlist null if empty

    //join playlist id to playlist id
    @Column(name="Playlist") private Integer playlist;
    //Library Persistent ID
    //join column
    @Column(name="Library_ID") private String library_ID;


    //empty constructor
    public track(){}

    public track(Integer track_id, String name, String artist, String album, String genre,
                  Integer year, Integer size, String library_ID) {
        this.track_id = track_id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.year = year;
        this.size = size;
        this.library_ID = library_ID;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getTrack_id() {
        return track_id;
    }

    public void setTrack_id(Integer track_id) {
        this.track_id = track_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

   
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getLibrary_ID() {
        return library_ID;
    }

    public void setLibrary_ID(String library_ID) {
        this.library_ID = library_ID;
    }



    @Override
    public String toString(){
        return "Track ID: "+this.track_id + " Name: " +this.name + " Artist: " + this.artist + " Album: " + this.album + " Genre: "
                + this.genre  +  " Size: " + this.size + " Year: " + this.year + " Library: " + library_ID;
    }
}



