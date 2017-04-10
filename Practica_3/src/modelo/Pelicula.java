package modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pelicula{
	private String id;
	private String resumen;
	private String titulo;
	private int duracion;
	private int año;
	
	public Pelicula(){

	}

	public Pelicula (String id, String resumen, String titulo, int duracion, int año){
		this.id = id;
		this.resumen = resumen;
		this.titulo = titulo;
		this.duracion = duracion;
		this.año = año;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String title) {
		this.titulo = title;
	}
	
	public String getResumen() {
		return resumen;
	}

	public void setResumen(String res) {
		this.resumen = res;
	}
	
	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int dur) {
		this.duracion = dur;
	}
	
	public int getAño() {
		return año;
	}

	public void setAño(int anio) {
		this.año = anio;
	}

}