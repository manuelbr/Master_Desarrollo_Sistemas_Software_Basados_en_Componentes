package recursos;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import dao.PeliculaDao;
import modelo.Pelicula;

public class PeliculaRecurso {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	public PeliculaRecurso(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getPelicula() {
		Pelicula peli = PeliculaDao.instance.getProveedor().get(id);
		String resultado;
		
		if (peli == null) {
			throw new RuntimeException("Get: Pelicula con " + id + " no encontrada");
		}else{
			resultado = "<!DOCTYPE html><html><head><meta charset='"+"UTF-8"+"'><title>Resultados de Película</title></head><body><ul>";
			resultado =  resultado.concat("<li><h3>"+peli.getTitulo()+"</h3></br>"+"<p>Resumen: "+peli.getResumen()+"</p><p>Año de estreno: "+peli.getAño()+"</p><p>Duración :"+peli.getDuracion()+" minutos</p><form action='"+"http://localhost:8080/BlancoManuel-p3/rest/peliculas/"+peli.getId()+"' method='GET'><input type='submit' value='Eliminar'/></form>"+"</li></ul></body></html>");
		}
		return resultado;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Pelicula getPeli_XML() {
		Pelicula peli = PeliculaDao.instance.getProveedor().get(id);
		if (peli == null) {
			throw new RuntimeException("Pelicula con id: " + id + " no encontrada");
		}
		return peli;
	}
	
	//No se indica como método put, puesto que TOMCAT por defecto sólo acepta POST y GET. Aunque se han hecho las
	//modificaciones necesarias para que acepte put en el web.xml no han funcionado, por ello
	//el método de actualización se ha realizado con post.
	@POST
	@Path("actualiza")
	@Consumes(MediaType.APPLICATION_XML)
	public Response putPelicula(JAXBElement<Pelicula> pelis) {
		Pelicula peli = pelis.getValue();
		return putAndGetResponse(peli);
	}
	
	//No se indica como método delete, puesto que TOMCAT por defecto sólo acepta POST y GET. Aunque se han hecho las
	//modificaciones necesarias para que acepte delete en el web.xml no han funcionado, por ello
	//el método de actualización se ha realizado con get.
	@GET
	@Path("/{id}") 
	public void deletePelicula(@PathParam("id") String id,@Context HttpServletResponse servletResponse) throws IOException {
		Pelicula c = PeliculaDao.instance.getProveedor().remove(id);
		
		System.out.println("ELMINANDOOOOOO");
		
		if (c == null) 
			throw new RuntimeException("Delete: Pelicula con " + id + " no encontrada");
		else
			servletResponse.sendRedirect("http://localhost:8080/BlancoManuel-p3/rest/peliculas");
	}
	
	private Response putAndGetResponse(Pelicula peli) {
		Response res;
		if (PeliculaDao.instance.getProveedor().containsKey(peli.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		PeliculaDao.instance.getProveedor().put(peli.getId(), peli);
		return res;
	}

}
