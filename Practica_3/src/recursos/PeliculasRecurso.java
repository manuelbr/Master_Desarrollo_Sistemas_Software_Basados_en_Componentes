package recursos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import dao.PeliculaDao;
import modelo.Pelicula;

@Path("/peliculas")
public class PeliculasRecurso {
	 @Context
     UriInfo uriInfo;
     @Context
     Request request;
     String id;

 	//No se indica como m�todo delete, puesto que TOMCAT por defecto s�lo acepta POST y GET. Aunque se han hecho las
 	//modificaciones necesarias para que acepte delete en el web.xml no han funcionado, por ello
 	//el m�todo de actualizaci�n se ha realizado con get.
     @GET
     @Path("/{id}") 
     public void eliminaPelicula(@PathParam("id") String id,@Context HttpServletResponse servletResponse) throws IOException {
    	 	Pelicula c = PeliculaDao.instance.getProveedor().remove(id);
             
            if(c==null)
                     throw new RuntimeException("No se ha encontrado la pel�cula con id " + id);
             else
            	 servletResponse.sendRedirect("http://localhost:8080/BlancoManuel-p3/rest/peliculas");
     }
     
     
     //Devuelve la lista de pel�culas al usuario en el buscador en HTML.
     @GET
     @Produces(MediaType.TEXT_HTML)
     public String getPeliculas() {
    	 	 ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
             peliculas.addAll(PeliculaDao.instance.getProveedor().values());
             
             String resultado = "<!DOCTYPE html><html><head><meta charset='"+"UTF-8"+"'><title>Resultados de lista de pel�culas</title></head><body><ul>";
             
             for(int i = 0; i<peliculas.size(); i++){
            	resultado =  resultado.concat("<li><h3>"+peliculas.get(i).getTitulo()+"</h3></br>"+"<p>Resumen: "+peliculas.get(i).getResumen()+"</p><p>A�o de estreno: "+peliculas.get(i).getA�o()+"</p><p>Duraci�n :"+peliculas.get(i).getDuracion()+" minutos</p><form action='"+"http://localhost:8080/BlancoManuel-p3/rest/peliculas/"+peliculas.get(i).getId()+"' method='GET'><input type='submit' value='Eliminar'/></form>"+"</li>");
             }
             
             resultado = resultado.concat("</ul></body></html>");
             return resultado;
     }

     //Devuelve la lista de pel�culas en formato xml o Json para utilizarlo
     @GET
     @Produces(MediaType.TEXT_XML)
     public List<Pelicula> getPeli_XML() {
             ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
             peliculas.addAll(PeliculaDao.instance.getProveedor().values());
             return peliculas;
     }

     //Devuelve el n�mero de pel�culas
     @GET
     @Path("cantidad")
     @Produces(MediaType.TEXT_PLAIN)
     public String getCount() {
             int cantidad = PeliculaDao.instance.getProveedor().size();
             return String.valueOf(cantidad);
     }

     //Inserta una nueva pel�cula a trav�s del formulario HTML.
     @POST
     @Path("inserta")
     @Produces(MediaType.TEXT_HTML)
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     public void nuevaPelicula(@FormParam("id") String id, @FormParam("resumen") String resumen,@FormParam("titulo") String titulo,@FormParam("duracion") String duracion,@FormParam("a�o") String a�o,@Context HttpServletResponse servletResponse) throws IOException {
             Pelicula peli = new Pelicula(id, resumen,titulo,Integer.parseInt(duracion),Integer.parseInt(a�o));
             PeliculaDao.instance.getProveedor().put(id, peli);
             servletResponse.sendRedirect("http://localhost:8080/BlancoManuel-p3/rest/peliculas");
     }

     @Path("{pelicula}")
     public PeliculaRecurso getPelicula(@PathParam("pelicula") String id) {
             return new PeliculaRecurso(uriInfo, request, id);
     }
}
