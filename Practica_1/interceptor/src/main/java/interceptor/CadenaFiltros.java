package interceptor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

//Representa el tipo de dato de cadena de filtros que almacenan todos los que se quieran definir. Implementa la interfaz filtros.
public class CadenaFiltros {
	private ArrayList<Filtro> filtros;
	private Interfaz objetivo;
	
	public CadenaFiltros(){
		filtros = new ArrayList<Filtro>();
	}
	
	//Añade un filtro a la cadena de filtros
	public void addFiltro(Filtro fil){
		filtros.add(fil);
	}
	
	//Ejecuta todos los filtros que se han creado desde el main.
	public void ejecutar(double peticion) throws IOException, URISyntaxException{
		for(Filtro f:filtros){
			System.out.println("Nueva velocidad (m/s) "+f.ejecutar(peticion));
		}
		objetivo.ejecutar(peticion);
	}
	
	//Fija el objetivo del patrón interceptor
	public void setObjetivo(Interfaz objet){
		objetivo = objet;
	}
}
