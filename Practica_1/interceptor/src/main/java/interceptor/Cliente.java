package interceptor;

import java.io.IOException;
import java.net.URISyntaxException;

//Hace las veces del cliente que env�a las peticiones al gestor de filtros para que se ejecuten.
public class Cliente {
	private GestorFiltros gF;
	
	//Env�a la petici�n al gestor de filtros
	public void enviarPeticion(double peticion) throws IOException, URISyntaxException{
		gF.ejecutarPeticionFiltrosObjetivo(peticion);
	}
	
	//Fija cu�l es el gestor de filtros
	public void setGestorFiltros(GestorFiltros gf){
		gF = gf;
	}
}
