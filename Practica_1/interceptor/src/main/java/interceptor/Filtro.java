package interceptor;

//Interfaz de filtros.
public interface Filtro {
	int INTERVALO = 50;
	int RADIO = 5;
	
	public double ejecutar(Object peticion);
}
