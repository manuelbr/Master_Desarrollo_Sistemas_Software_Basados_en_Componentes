package interceptor;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


//El objetivo del patrón interceptor
public class Interfaz {
	
	public void ejecutar(double peticion){
		//Dirección URL con la página que debe abrirse sespués de finalizar el proceso.
        String url = "http://localhost:8080/interceptor/home.jsf";
        
        try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
