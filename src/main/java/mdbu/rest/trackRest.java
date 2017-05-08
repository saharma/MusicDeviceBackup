package mdbu.rest;

import mdbu.ejb.trackService;
import mdbu.entities.Track;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;


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
    public void delete(@PathParam("id") Integer track_id) {
        trackService.remove(track_id);
    }


        @POST @Path("/upload")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        public Response upload(MultipartFormDataInput input) throws IOException {
            // relative path not working
            String path = "res/incoming_data/";

            path = "/Users/saharmohamedali/Git_Repository/MusicDeviceBackUp/res/incoming_data/";
            String fileName = "";

            Map<String, List<InputPart>> formParts = input.getFormDataMap();
            List<InputPart> inPart = formParts.get("file");

            for (InputPart inputPart : inPart) {

                try {
                    // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
                    MultivaluedMap<String, String> headers = inputPart.getHeaders();
                    fileName = parseFileName(headers);
                    // Handle the body of that part with an InputStream
                    InputStream istream = inputPart.getBody(InputStream.class,null);
                    fileName = path + fileName;
                    saveFile(istream,fileName);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String output = "File saved to server location : " + fileName;
            return Response.status(200).entity(output).build();
        }

        // Parse Content-Disposition header to get the original file name
    private String parseFileName(MultivaluedMap<String, String> headers) {
        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"","");
                return fileName;
            }
        }
        return "randomName";
    }

    // save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream, String serverLocation) {
        try {
            OutputStream outputStream;
            int read = 0;
            byte[] bytes = new byte[1024];

            outputStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




