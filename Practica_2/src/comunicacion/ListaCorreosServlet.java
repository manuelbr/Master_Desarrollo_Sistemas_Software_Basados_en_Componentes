package comunicacion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.BDUsuario;
import modelo.Usuario;

/**
 * Servlet implementation class ListaCorreosServlet
 */
@WebServlet("/ListaCorreosServlet")
public class ListaCorreosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ListaCorreosServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BDUsuario bdu = new BDUsuario();
		List<Usuario> res;
		try {
			res = bdu.listarUsuarios();
			PrintWriter out = response.getWriter();
            out.append("<html><head><title>Usuarios Registrados</title></head>");
            out.append("<body><h1>Usuarios</h1><table>");
            out.append("<tr><td>Nombre</td><td>Apellidos</td><td>Email</td></tr>");
            for(Usuario u: res){
            	out.append("<tr>"+"<td>"+u.getNombre()+"</td>"+"<td>"+u.getApellido()+"</td>"+"<td>"+u.getEmail()+"</td>"+"</tr>");
            }
            out.append("</table>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
		if(request.getParameter("action") != null)
			doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String peticion = request.getParameter("action");
		ObjectOutputStream objectOutput;
		BDUsuario bdu = new BDUsuario();
		String nombre;
		String apellido;
		String email;
		Usuario u;
		
		switch(peticion){
			case "aniadirUsuario": nombre = request.getParameter("nombre");
							apellido = request.getParameter("apellido");
							email = request.getParameter("email");
							u = new Usuario();
							u.setNombre(nombre);
							u.setApellido(apellido);
							u.setEmail(email);
							bdu.insertar(u);
							break;
			case "eliminarUsuario": email = request.getParameter("email");
						 	u = new Usuario(bdu.seleccionarUsuario(email));
							bdu.eliminar(u);
							break;
			case "actualizarUsuario": nombre = request.getParameter("nombre");
							apellido = request.getParameter("apellido");
							email = request.getParameter("email");
							u = new Usuario();
							u.setNombre(nombre);
							u.setApellido(apellido);
							u.setEmail(email);
							bdu.actualizar(u);
							break;
			case "listarUsuarios": 
							try {
								List<Usuario> res = bdu.listarUsuarios();
								objectOutput = new ObjectOutputStream(response.getOutputStream());
					            objectOutput.writeObject(res);
					            objectOutput.flush();
					            objectOutput.close();
					            //response.getWriter().append("S ").append(request.getContextPath());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
			case "existeEmail": 
							try {
								email = request.getParameter("email");
								boolean respuesta = bdu.existeEmail(email);
								objectOutput = new ObjectOutputStream(response.getOutputStream());
					            objectOutput.writeBoolean(respuesta);
					            objectOutput.flush();
					            objectOutput.close();
							} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
							break;
								
		}
		
		if(!peticion.equals("listarUsuarios") && !peticion.equals("existeEmail")){
			//Respuesta por defecto después de cada petición desde el cliente, mientras no sea una petición de listarUsuarios.
			objectOutput = new ObjectOutputStream(response.getOutputStream());
	        objectOutput.writeInt(0);
	        objectOutput.writeObject("HA OCURRIDO UN PROBLEMA");
	        objectOutput.flush();
	        objectOutput.close();
		}
	}

}
