package IoT.esir.restprj.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.FileNotFoundException;

@Path("/chenillard")
public class EntryPoint {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "TestEE";
    }
    
    
    @GET
    @Path("xml")
    @Produces(MediaType.TEXT_HTML)
    public File xmlFile() throws FileNotFoundException {
    	File doc = new File("page.html");
    	return doc;
    	
//    	@POST
//        public Response post(Product product) {
//            Products.put(product);
//            return Response.ok(product).build();
//        }	
//    	
    	
    }
    
    
}