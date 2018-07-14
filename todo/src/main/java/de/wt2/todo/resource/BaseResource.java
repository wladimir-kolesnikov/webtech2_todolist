package de.wt2.todo.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import de.wt2.todo.entity.User;

public abstract class BaseResource<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	/*
	 * Abstrakte Methode muss erst in Subklassen implementiert werden, wird jedoch standardmäßig auf den @Path/id
	 * der API via get request gelegt. @Path wird erst in den Subklassen implementiert und "id" ist jeweils die ID
	 * nach der gesucht werden soll
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public abstract T find(@PathParam("id") long id);
	
	/*
	 * Abstrakte Methode muss erst in Subklassen implementiert werden, wird jedoch standardmäßig auf den @Path
	 * der API via get request gelegt. @Path wird erst in den Subklassen implementiert
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public abstract List<T> findAll();
	
	
//	/*
//	 * Basic CRUD operations
//	 */
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public T create(final T t) {
//		entityManager.persist(t);
//		return t;
//	}
//	
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public T update(final T t) {
//		entityManager.merge(t);
//		return t;
//	}
//	
//	@DELETE
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public T delete(final T t) {
//		entityManager.remove(t);
//		return t;
//	}
	
	/*
	 * Checkt, ob der eingeloggte User auch der User 
	 * mit der übergebenen ID in der REST Api, bzw. Admin, ist
	 * Somit wird verhindert, dass z.B. User1 die Notes von User2 löscht 
	 * (URL der Rest API kann einfach geraten werden, da Muster vorhersehbar ist, z.B. /users/1/note/1)
	 */
	protected boolean isAllowed(long userID) {	
		Subject currentUser = SecurityUtils.getSubject();
		
		if (currentUser.isAuthenticated()) {
			User sessionUser = (User) currentUser.getSession().getAttribute("user");
	        User user = entityManager.find(User.class, userID);
	        
	        if (currentUser.hasRole("admin") || sessionUser.getId() == user.getId()) return true;
	        else return false;
		} else return false;
    	
	}
}
