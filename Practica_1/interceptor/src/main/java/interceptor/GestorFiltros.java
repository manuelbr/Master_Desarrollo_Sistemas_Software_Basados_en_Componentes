package interceptor;

import java.io.IOException;
import java.net.URISyntaxException;

public class GestorFiltros {
	private CadenaFiltros cd;
	
	public GestorFiltros(Interfaz objetivo){
		cd = new CadenaFiltros();
		cd.setObjetivo(objetivo);
	}
	
	//Se fija el filtro
	public void setFiltro(Filtro f){
		cd.addFiltro(f);
	}
	
	//Se envía la petición a la cadena de filtros para que ejecuten todos esa petición.
	public void ejecutarPeticionFiltrosObjetivo(double peticion) throws IOException, URISyntaxException{
		cd.ejecutar(peticion);
	}
}
