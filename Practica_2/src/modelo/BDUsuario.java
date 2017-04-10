package modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BDUsuario {
	private static final String PERSISTENCE_UNIT_NAME = "usuario";
	private static EntityManagerFactory factoria;
	
	public static void insertar(Usuario u){
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("SELECT u from Usuario u where u.email = :email");
		boolean usuarioNoRegistrado = (q.setParameter("email",u.getEmail()).getResultList().size() == 0);
		
		if(usuarioNoRegistrado){
			em.persist(u);
		}else
			System.out.println("Tupla ya introducida");
		
		
		tx.commit();
		em.close();
	}
	
	public static void eliminar(Usuario u){
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("select u from Usuario u where u.email = :email");
		boolean usuarioYaRegistrado = (q.setParameter("email",u.getEmail()).getResultList().size() == 1);
	
		if(usuarioYaRegistrado){
			Usuario aEliminar = (Usuario) q.setParameter("email",u.getEmail()).getSingleResult();
			if (!em.contains(aEliminar)) {
			    u = em.merge(aEliminar);
			}
			em.remove(aEliminar);
		}else
			System.out.println("Usuario no existe");
		
		tx.commit();
		em.close();	
	}
	
	public static void actualizar(Usuario u){
		eliminar(u);
		insertar(u);
	}
	
	public static Usuario seleccionarUsuario(String email){
		Usuario u = null;
		
		if(existeEmail(email)){
			factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = factoria.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Query q = em.createQuery("select u from Usuario u where u.email = :email");
			u = (Usuario) q.setParameter("email", email).getSingleResult();
			
			tx.commit();
			em.close();
		}
		
		return u;
	}

	public static boolean existeEmail(String email){
		boolean resultado = false;
		
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("select u.email from Usuario u where u.email = :email");
		
		if(q.setParameter("email", email).getResultList().size() == 0)
			resultado = false;
		else
			resultado = true;
		
		tx.commit();
		em.close();
		
		return resultado;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() throws Exception{
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u");
		List<Usuario> listaResultados = (List<Usuario>) q.getResultList();
		
		em.close();
		 
		return listaResultados;
	}
}
