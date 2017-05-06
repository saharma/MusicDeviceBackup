package mdbu.rest;

import mdbu.ejb.trackService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by saharmohamedali on 05/05/2017.
 */
@Path("/track")
public class trackRest {

    @EJB
    trackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON) @Path("/getAllTracks")
    public Collection<?> getAllTracks(){
        return trackService.getAllTracks();
    }

}
