package interceptor;

//Filtro que calcula la velocidad en función del parámetro intervalo
//OJO: El intervalo ha sido fijado de forma aleatoria. Implementa la interfaz filtros.
public class Calcular implements Filtro{
	
	//Se calcula la velocidad con las revoluciones que se indican desde el main y el valor del atributo Intervalo.
	public double ejecutar(Object o) {
		double distancia = (double) o;
		double velocidad = distancia*3600/INTERVALO;
		System.out.println(velocidad);
		return velocidad;
	}
}
