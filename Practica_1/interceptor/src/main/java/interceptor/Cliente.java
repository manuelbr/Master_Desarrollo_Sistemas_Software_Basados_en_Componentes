package interceptor;

import java.io.IOException;
import java.net.URISyntaxException;

//Hace las veces del cliente que envía las peticiones al gestor de filtros para que se ejecuten.
public class Cliente {
	private GestorFiltros gF;
	
	//Envía la petición al gestor de filtros
	public void enviarPeticion(double peticion) throws IOException, URISyntaxException{
		gF.ejecutarPeticionFiltrosObjetivo(peticion);
	}
	
	//Fija cuál es el gestor de filtros
	public void setGestorFiltros(GestorFiltros gf){
		gF = gf;
	}
}
