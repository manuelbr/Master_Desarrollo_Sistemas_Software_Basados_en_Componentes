package interceptor;

//Otro tipo de filtro (No usado en este práctica) que tiene como objetivo calcular la distancia que se recorre con 
//un número determinado de revoluciones y un valor del atributo radio
public class CalcularDistancia implements Filtro{
	private double revolAnt = 0; //Revoluciones anteriores
	
	public double ejecutar(Object o) {
		double revoluciones = (double) o;
		double distancia = (revoluciones-revolAnt)*2*RADIO*3.1416;
		revolAnt = revoluciones;
		
		return distancia;
	}

}
