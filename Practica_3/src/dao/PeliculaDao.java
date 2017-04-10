package dao;

import java.util.HashMap;
import java.util.Map;

import modelo.Pelicula;

public enum PeliculaDao {

  instance;  //Para que la clase sea singleton

  private Map<String, Pelicula> contentProvider = new HashMap<String, Pelicula>();

  private PeliculaDao() {
	//Película 1 de inicio creada
    Pelicula peli = new Pelicula("1", "Treinta años después de la victoria de la Alianza Rebelde sobre la segunda Estrella de la Muerte (hechos narrados en el Episodio VI: El retorno del Jedi), la galaxia está todavía en guerra. Una nueva República se ha constituido, pero una siniestra organización, la Primera Orden, ha resurgido de las cenizas del Imperio Galáctico. A los héroes de antaño, que luchan ahora en la Resistencia, se suman nuevos héroes: Poe Dameron, un piloto de caza, Finn, un desertor de la Primera Orden, Rey, una joven chatarrera, y BB-8, un androide rodante. Todos ellos luchan contra las fuerzas del Mal: el Capitán Phasma, de la Primera Orden, y Kylo Ren, un temible y misterioso personaje que empuña un sable de luz roja.", "Star Wars VII", 135,2015);
    contentProvider.put("1", peli);

    //Película 2 de inicio creada
    peli = new Pelicula("2", "Al ver que la vida en la Tierra está llegando a su fin, un grupo de exploradores dirigidos por el piloto Cooper (McConaughey) y la científica Amelia (Hathaway) emprenden una misión que puede ser la más importante de la historia de la humanidad: viajan más allá de nuestra galaxia para descubrir otra que pueda garantizar el futuro de la raza humana.","Interstellar",169,2014);
    contentProvider.put("2", peli); 
  }

  public Map<String, Pelicula> getProveedor(){
    return contentProvider;
  }

} 
