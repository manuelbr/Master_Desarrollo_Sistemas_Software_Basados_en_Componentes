package interceptor;

import java.io.IOException;
import java.net.URISyntaxException;

public class DemoInterceptor {

	public static void main(String[] args) throws IOException, URISyntaxException {
		GestorFiltros gestorFiltros = new GestorFiltros(new Interfaz()); //se crea el gestor de filtros, especificando su objetivo.
		gestorFiltros.setFiltro(new Calcular()); //Se a�ade el filtro.
		Cliente cliente = new Cliente(); //Se crea el cliente que enviar� la petici�n de comienzo.
		cliente.setGestorFiltros(gestorFiltros); //El cliente fija el gestor de filtros que usar�.
		cliente.enviarPeticion(500); //El cliente env�a la petici�n. (500 revoluciones iniciales). 
	}

}
