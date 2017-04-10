package cliente;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import modelo.Pelicula;

public class Probador {
	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget service = client.target(getBaseURI());
        Response response;
        
        //Se actualiza una pel�cula
        Pelicula peli = new Pelicula("1","Hace ocho a�os que Batman desapareci�, dejando de ser un h�roe para convertirse en un fugitivo. Al asumir la culpa por la muerte del fiscal del distrito Harvey Dent, el Caballero Oscuro decidi� sacrificarlo todo por lo que consideraba, al igual que el Comisario Gordon, un bien mayor. La mentira funciona durante un tiempo, ya que la actividad criminal de la ciudad de Gotham se ve aplacada gracias a la dura Ley Dent. Pero todo cambia con la llegada de una astuta gata ladrona que pretende llevar a cabo un misterioso plan. Sin embargo, mucho m�s peligrosa es la aparici�n en escena de Bane, un terrorista enmascarado cuyos despiadados planes obligan a Bruce a regresar de su voluntario exilio.","El caballero Oscuro: La Leyenda Renace",164,2012);
        response = service.path("rest").path("peliculas").path(peli.getId()).path("actualiza").request().post(Entity.entity(peli,MediaType.APPLICATION_XML),Response.class);

        //Se comprueba la sustituci�n de la pel�cula con id 1
        System.out.println(service.path("rest").path("peliculas").request().accept(MediaType.TEXT_XML).get(String.class));
        
        //Elimina la pel�cula con id 1
        service.path("rest").path("peliculas/1").request().delete();

        //Vuelve a mostrar todas las pel�culas (No aparece la que tiene id = 1)
        System.out.println(service.path("rest").path("peliculas").request().accept(MediaType.TEXT_XML).get(String.class));

        //Inserta nueva pel�cula
        Form form =new Form();
        form.param("id", "4");
        form.param("resumen","En el a�o 180, el Imperio Romano domina todo el mundo conocido. Tras una gran victoria sobre los b�rbaros del norte, el anciano emperador Marco Aurelio (Richard Harris) decide transferir el poder a M�ximo (Russell Crowe), bravo general de sus ej�rcitos y hombre de inquebrantable lealtad al imperio. Pero su hijo C�modo (Joaquin Phoenix), que aspiraba al trono, no lo acepta y trata de asesinar a M�ximo.");
        form.param("titulo", "Gladiator");
        form.param("duracion", "180");
        form.param("a�o", "2000");
        response = service.path("rest").path("peliculas").path("inserta").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class);
        System.out.println("Respuesta de inserci�n " + response.getStatusInfo());

        //Vuelve a mostrar todas las pel�culas, ahora incluyendo la que tiene id 4
        System.out.println(service.path("rest").path("peliculas").request().accept(MediaType.TEXT_XML).get(String.class));
	}
		
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/BlancoManuel-p3").build();
	}
}
