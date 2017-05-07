package mdbu.rest;

import mdbu.ejb.trackService;
import mdbu.entities.Track;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by saharmohamedali on 05/05/2017.
 */
@Path("/track")
public class trackRest {

    @EJB
    trackService trackService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
     public void create(Track track) {
                trackService.add(track);
            }

    @GET
    @Produces(MediaType.APPLICATION_JSON) @Path("/{id}")
     public Track read(@PathParam("id") Integer id) {
                return trackService.getById(id);
            }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllTracks")
    public Collection<?> getAllTracks(){

        return trackService.getAllTracks();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
     public void update(Track track) {
        trackService.update(track);
            }

    @DELETE @Path("/delete/{id}")
    public void delete(@PathParam("id") Integer track_id){
        trackService.remove(track_id);
    }
}
