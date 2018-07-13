package de.wt2.todo.resource;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userID}/notes")
	public List<Note> findAll(@PathParam("userID") long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Note.class);
		Root<User> user = cq.from(User.class);
		//Join<User, Note> notes = user.join(User_.notes);
		cq.where(user.get(User_.id).in(id));
		
		cq.select(user.get(User_.notes));
		TypedQuery<Note> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}
	
	
	/*
	 * FOR TESTING PURPOSES ONLY! 
	 * SHOULD BE DELETED IF SHIRO IS READY
	 */
	@GET
	@Path("/login/{username}/{password}")
	public String login(@PathParam("username") String username, @PathParam("password") String password) {
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated() ) {
		    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		    token.setRememberMe(true);
		    
		    try {
			    currentUser.login(token);
			    return "LOGGED IN!!!";
		    } catch (UnknownAccountException uae) {
		    	return "Unknown user!";
		    } catch (IncorrectCredentialsException ice) {
		    	return "Incorrect credentials!";
		    } catch (AuthenticationException ae) {
		    	return "Unknown error!";
		    }
		} else {
			return "Already logged in!";
		}
	}
}
