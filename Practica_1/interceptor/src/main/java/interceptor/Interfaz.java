package interceptor;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


//El objetivo del patr�n interceptor
public class Interfaz {
	
	public void ejecutar(double peticion){
		//Direcci�n URL con la p�gina que debe abrirse sespu�s de finalizar el proceso.
        String url = "http://localhost:8080/interceptor/home.jsf";
        
        try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
