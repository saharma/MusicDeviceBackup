package mdbu.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by saharmohamedali on 02/05/2017.
 */

@Entity
@Table(name="Playlist")
public class Playlist implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name") private String name;
    @Column(name="playlist_ID") private Integer playlist_ID;

    //Library Persistent ID
    //join to Library
    @Column(name="Library")private Integer library;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlaylist_ID() {
        return playlist_ID;
    }

    public void setPlaylist_ID(Integer ID) {
        this.playlist_ID = playlist_ID;
    }

    public Integer getLibrary() {
        return library;
    }

    public void setLibrary(Integer ID) {
        this.library = library;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Playlist() {
    }

    public Playlist(String name, Integer ID) {
        this.name = name;
        this.playlist_ID = playlist_ID;
    }
}














