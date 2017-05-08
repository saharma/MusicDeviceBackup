package mdbu.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;
import java.io.Serializable;


@NamedQueries({
        @NamedQuery(name = "getUser", query = "from User user where user.username = :username and user.password= :password")
})

@Entity
@Table(name="user")
public class User  implements Serializable {
    @Column(name="Username")        private String username;

    @Id @Column(name="User_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer userId;
    @Column(name="Password")        private String password;
    @Column(name="Library_persistent_ID")    private String library_persistent_id;


    public User(String username, String password, String library_persistent_id){
        this.username = username;
        this.password = password;
        this.library_persistent_id = library_persistent_id;
    }

    public String Login(String username, String password)
    {
        return "username:" + username + " " + "Password:" + password;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLibrary_persistent_id() {
        return library_persistent_id;
    }



    public void setLibrary_persistent_id(String library_persistent_id) {
        this.library_persistent_id = library_persistent_id;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", library_persistent_id=" + library_persistent_id + '\'' +
                '}';
    }
}