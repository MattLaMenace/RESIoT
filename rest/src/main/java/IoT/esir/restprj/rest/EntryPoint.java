package IoT.esir.restprj.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import tuwien.auto.calimero.KNXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.module.ResolutionException;

@Path("/chenillard")
public class EntryPoint {

	public static MyThread thread;


	@GET
	@Path("xml")
	@Produces(MediaType.TEXT_HTML)
	public File xmlFile() throws FileNotFoundException, ResolutionException, KNXException, InterruptedException,KNXException, InterruptedException {
		File doc = new File("page.html");
		thread = new MyThread(800);
		return doc;
	}

	@GET
	@Path("/chenillardv1")
	@Produces(MediaType.TEXT_HTML)
	public void chenillardV1() throws FileNotFoundException, ResolutionException, KNXException,InterruptedException, KNXException, InterruptedException {
		thread.myStop();
		thread = new MyThread(800);
		thread.chenillardV1();
	}
	
	@GET
	@Path("/chenillardv2")
	@Produces(MediaType.TEXT_HTML)
	public void chenillardV2() throws FileNotFoundException, ResolutionException, KNXException,InterruptedException, KNXException, InterruptedException {
		thread.myStop();
		thread = new MyThread(800);
		thread.chenillardV2();
	}
	
	@GET
	@Path("/chenillardv3")
	@Produces(MediaType.TEXT_HTML)
	public void chenillardV3() throws FileNotFoundException, ResolutionException, KNXException,InterruptedException, KNXException, InterruptedException {
		thread.myStop();
		thread = new MyThread(800);
		thread.chenillardV3();
	}
	
	@GET
	@Path("/chenillardv4")
	@Produces(MediaType.TEXT_HTML)
	public void chenillardV4() throws FileNotFoundException, ResolutionException, KNXException,InterruptedException, KNXException, InterruptedException {
		thread.myStop();
		thread = new MyThread(800);
		thread.chenillardV4();
	}
	
	
	
	@GET
	@Path("/eteindre")
	@Produces(MediaType.TEXT_HTML)
	public void eteindre() throws FileNotFoundException, ResolutionException, KNXException,InterruptedException, KNXException, InterruptedException {
		thread.myStop();
		thread.eteindre();
	}
	
	@GET
	@Path("/vitesseUP")
	@Produces(MediaType.TEXT_HTML)
	public void vitesseUP() throws FileNotFoundException, ResolutionException, KNXException,InterruptedException, KNXException, InterruptedException {
		thread.accelerer();
	}
	
	@GET
	@Path("/vitesseDOWN")
	@Produces(MediaType.TEXT_HTML)
	public void vitesseDOWN() throws FileNotFoundException, ResolutionException, KNXException,InterruptedException, KNXException, InterruptedException {
		thread.decelerer();
	}
	
	@GET
	@Path("/logOut")
	@Produces(MediaType.TEXT_HTML)
	public void logOut() throws FileNotFoundException, ResolutionException, KNXException,InterruptedException, KNXException, InterruptedException {
		thread.myStop();
		thread.close();
		xmlFile();
	}
	
	

}