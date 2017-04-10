package dao;

import java.util.HashMap;
import java.util.Map;

import modelo.Pelicula;

public enum PeliculaDao {

  instance;  //Para que la clase sea singleton

  private Map<String, Pelicula> contentProvider = new HashMap<String, Pelicula>();

  private PeliculaDao() {
	//Pel�cula 1 de inicio creada
    Pelicula peli = new Pelicula("1", "Treinta a�os despu�s de la victoria de la Alianza Rebelde sobre la segunda Estrella de la Muerte (hechos narrados en el Episodio VI: El retorno del Jedi), la galaxia est� todav�a en guerra. Una nueva Rep�blica se ha constituido, pero una siniestra organizaci�n, la Primera Orden, ha resurgido de las cenizas del Imperio Gal�ctico. A los h�roes de anta�o, que luchan ahora en la Resistencia, se suman nuevos h�roes: Poe Dameron, un piloto de caza, Finn, un desertor de la Primera Orden, Rey, una joven chatarrera, y BB-8, un androide rodante. Todos ellos luchan contra las fuerzas del Mal: el Capit�n Phasma, de la Primera Orden, y Kylo Ren, un temible y misterioso personaje que empu�a un sable de luz roja.", "Star Wars VII", 135,2015);
    contentProvider.put("1", peli);

    //Pel�cula 2 de inicio creada
    peli = new Pelicula("2", "Al ver que la vida en la Tierra est� llegando a su fin, un grupo de exploradores dirigidos por el piloto Cooper (McConaughey) y la cient�fica Amelia (Hathaway) emprenden una misi�n que puede ser la m�s importante de la historia de la humanidad: viajan m�s all� de nuestra galaxia para descubrir otra que pueda garantizar el futuro de la raza humana.","Interstellar",169,2014);
    contentProvider.put("2", peli); 
  }

  public Map<String, Pelicula> getProveedor(){
    return contentProvider;
  }

} 
