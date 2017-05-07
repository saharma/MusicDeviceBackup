package mdbu.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * Created by saharmohamedali on 02/05/2017.
 */
public class library implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="library_persistent_id") private Integer library_persistent_id;

    public library(){}

    public library(Integer library_persistent_id){
        this.library_persistent_id= library_persistent_id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLibrary_persistent_id() {
        return library_persistent_id;
    }

    public void setLibrary_persistent_id(Integer library_persistent_id) {
        this.library_persistent_id = library_persistent_id;
    }


}
