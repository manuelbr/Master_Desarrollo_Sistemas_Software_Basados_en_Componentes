package interceptor;

import java.io.IOException;
import java.net.URISyntaxException;

public class DemoInterceptor {

	public static void main(String[] args) throws IOException, URISyntaxException {
		GestorFiltros gestorFiltros = new GestorFiltros(new Interfaz()); //se crea el gestor de filtros, especificando su objetivo.
		gestorFiltros.setFiltro(new Calcular()); //Se añade el filtro.
		Cliente cliente = new Cliente(); //Se crea el cliente que enviará la petición de comienzo.
		cliente.setGestorFiltros(gestorFiltros); //El cliente fija el gestor de filtros que usará.
		cliente.enviarPeticion(500); //El cliente envía la petición. (500 revoluciones iniciales). 
	}

}
