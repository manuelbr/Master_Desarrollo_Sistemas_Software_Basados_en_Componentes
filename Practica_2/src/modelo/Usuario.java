package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long ID;
	private String nombre;
	private String apellido;
	private String email;
	
	public Usuario(){
		
	}
	
	public Usuario(Usuario u){
		setId(u.getId());
		setNombre(u.getNombre());
		setApellido(u.getApellido());
		setEmail(u.getEmail());
	}
	
	public void setId(long newId){
		ID = newId;
	}
	
	public long getId(){
		return ID;
	}
	
	public void setNombre(String newName){
		nombre = newName;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setApellido(String newApellido){
		apellido = newApellido;
	}
	
	public String getApellido(){
		return apellido;
	}
	
	public void setEmail(String newEmail){
		email = newEmail;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String toString(){
		return getNombre()+" "+getApellido()+"-"+getEmail();
	}
	
	
}
