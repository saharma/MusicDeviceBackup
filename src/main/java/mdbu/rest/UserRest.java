package mdbu.rest;

import mdbu.ejb.UserService;
import mdbu.entities.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by saharmohamedali on 08/05/2017.
 */
@Path("/user")
public class UserRest {
    @EJB
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON) @Path("/{username}/{password}")
    public User getUser(@PathParam("username") String username, @PathParam("password") String password){
        return userService.getUser(username,password);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(User user) {
        userService.addUser(user);
    }
}