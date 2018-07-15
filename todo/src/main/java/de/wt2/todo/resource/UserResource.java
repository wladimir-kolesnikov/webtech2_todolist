package de.wt2.todo.resource;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import de.wt2.todo.entity.Note;
import de.wt2.todo.entity.User;
import de.wt2.todo.entity.User_;

@Transactional
@Stateless
@Path("/users")
public class UserResource extends BaseResource<User> {

	public User find(long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		CriteriaQuery<User> query = entityManager.getCriteriaBuilder().createQuery(User.class);
		query.select(query.from(User.class));
		
		Query q = entityManager.createQuery(query);
		return q.getResultList();
	}
	
	/*
	 * Erstellt einen neuen Benutzer. 
	 * Das Password wird encrypted in der Datenbank gespeichert
	 */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(final User user) {
        PasswordService ps = new DefaultPasswordService();
        user.setPassword(ps.encryptPassword(user.getPassword()));
        entityManager.persist(user);
        return user;
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userID}/notes")
	public List<Note> findAll(@PathParam("userID") long userID) {
		
		/*
		 * Einfach auskommentieren, falls man die Berechtigung doch einschränken will
		 */
//		if (isAllowed(userID)) {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery(Note.class);
			Root<User> user = cq.from(User.class);
			cq.where(user.get(User_.id).in(userID));
			cq.select(user.get(User_.notes));
			TypedQuery<Note> tq = entityManager.createQuery(cq);
			return tq.getResultList();
//		} else {
//			return null;
//		}
	}
	
	/*
	 * Erstellt eine Note für den entsprechenden User
	 * 
	 */
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userID}/notes")
    public Note create(final Note t, @PathParam("userID") long userID) {
		if (isAllowed(userID)) {
			entityManager.persist(t);
	        User user = entityManager.find(User.class, userID);
	        t.setAuthorUser(user);
	        t.setAuthor(user.getUsername());
	        user.getNotes().add(t);

	        entityManager.merge(user);
	        return t;
		} else return null;
    }


	/*
	 * Updated eine Note, z.B. wenn die Relevance oder das Datum geändert wird
	 */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userID}/notes/{noteID}")
    public Note update(final Note t, @PathParam("userID") long userID) {
    	if (isAllowed(userID)) {
        	entityManager.merge(t);
            return t;
        } else return null;
    }
	
	/*
	 * Updated einen User, z.B. wenn das Passwort geändert wird.
	 */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userID}")
    public User update(final User user, @PathParam("userID") long userID) {
    	if (isAllowed(userID)) {
    		 User thisUser = entityManager.find(User.class, userID);
    	        PasswordService ps = new DefaultPasswordService();
    	        thisUser.setPassword(ps.encryptPassword(user.getPassword()));
    	        entityManager.persist(user);
    	        return thisUser;
    	} else return null;
    }
    
    /*
     * Löscht eine Note eines Users
     */
	@DELETE
    @Path("/{userID}/notes/{noteID}")
    public void delete(@PathParam("userID") long userID, @PathParam("noteID") long noteID) {
		if (isAllowed(userID)) {
	        Note toDelete = entityManager.find(Note.class, noteID);
	        toDelete.setAuthorUser(null);
	        entityManager.remove(toDelete);
		}
    }
}
