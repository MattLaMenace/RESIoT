package IoT.esir.restprj.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.FileNotFoundException;

@Path("/entry-point")
public class EntryPoint {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test";
    }
    
    
    @GET
    @Path("xml")
    @Produces(MediaType.TEXT_HTML)
    public File xmlFile() throws FileNotFoundException {
    	File doc = new File("page.html");
    	return doc;
    	
    }
    
    
}