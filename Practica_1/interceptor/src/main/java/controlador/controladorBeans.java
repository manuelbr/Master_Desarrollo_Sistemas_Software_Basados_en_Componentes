package controlador;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "controladorBeans", eager = true)
@RequestScoped
public class controladorBeans implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private enum estado{ENCENDIDO,APAGADO,ACELERANDO}
	private static String estadoActual = estado.APAGADO.toString(); 
	
	//Managed properties
	@ManagedProperty(value = "#{textoTitulo}")
	private String textoTitulo;
	@ManagedProperty(value = "#{textoBoton}")
	private String textoBoton;
	@ManagedProperty(value = "#{colorBoton}")
	private String colorBoton;
	@ManagedProperty(value = "#{colorTitulo}")
	private String colorTitulo;
	@ManagedProperty(value = "#{colorBotonA}")
	private String colorBotonA;
	
	///////////////////Getters y setters de los properties.
	public String getTextoTitulo(){
		if(textoTitulo == null)
			return "Motor de arranque apagado";
		else return textoTitulo;
	}
	
	public void setTextoTitulo(String tT){
		textoTitulo = tT;
	}
	
	public String getColorTitulo(){
		if(colorTitulo == null)
			return "blue";
		else return colorTitulo;
	}
	
	public void setColorTitulo(String c){
		colorTitulo = c;
	}
	
	public String getTextoBoton(){
		if(textoBoton == null)
			return "Encender";
		else return textoBoton;
	}
	
	public void setTextoBoton(String tB){
		textoBoton = tB;
	}
	
	public String getColorBoton(){
		if(colorBoton == null)
			return "blue";
		else return colorBoton;
	}
	
	public void setColorBoton(String cB){
		colorBoton = cB;
	}
	
	public String getColorBotonA(){
		if(colorBotonA == null)
			return "red";
		else return colorBotonA;
	}
	
	public void setColorBotonA(String cBA){
		colorBotonA = cBA;
	}
	
	
	//Action listener botón encender/apagar
	public void actualizarBotonOnOff(ActionEvent e){
		if(estadoActual == estado.APAGADO.toString()){
			textoTitulo = "Motor de arranque encendido";
			textoBoton = "Apagar";
			colorBotonA = "green";
			colorBoton ="red";
			estadoActual = estado.ENCENDIDO.toString();
		}else
			if(estadoActual == estado.ENCENDIDO.toString() || estadoActual == estado.ACELERANDO.toString()){
				textoBoton = "Encender";
				textoTitulo = "Motor de arranque apagado";
				colorBotonA = "red";
				colorBoton = "blue";
				estadoActual = estado.APAGADO.toString();
			}
	}
	
	//Action listener Botón acelerar
	public void actualizarBotonAcelerar(ActionEvent e){
		if(estadoActual == estado.ENCENDIDO.toString() || estadoActual == estado.ACELERANDO.toString()){
			estadoActual = estado.ACELERANDO.toString();
			textoTitulo = "Acelerando";
			textoBoton = "Apagar";
			colorBoton = "red";
			colorBotonA = "green";
		}
	}
}
