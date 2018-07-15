package de.wt2.todo.resource;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import de.wt2.todo.entity.User;
import de.wt2.todo.entity.User_;
import de.wt2.todo.model.LoginBean;

/*
 * Wird für den Login benutzt
 */

@Transactional
@Stateless
@Path("/login")
public class LoginResource {
	

	@PersistenceContext
	protected EntityManager entityManager;
	
	/*
	 * Checkt, ob die gesendeten Credentials gültig sind. Falls ja, wird der user eingeloggt
	 * und das eigene User Objekt in die Session übergeben.
	 * Das ist nötig für weitere checks, z.B. ob ein gesendeter Request auch wirklich von dem 
	 * in der REST API (z.B. über die ID) angegebenen User kommt
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(LoginBean loginBean) {
		Subject currentUser = SecurityUtils.getSubject();
		
	    UsernamePasswordToken token = new UsernamePasswordToken(loginBean.getUsername(), loginBean.getPassword());
	    token.setRememberMe(true);
	    
	    try {
		    currentUser.login(token);		
		    User user = findByName(loginBean.getUsername());
		    currentUser.getSession().setAttribute("user", user);
		    return user;
	    } catch (AuthenticationException ae) {
//	    	ae.printStackTrace();
	    	return null;
	    }
	}
	
	/*
	 * Hilfsmethode um den User in der Datenbank anhand des usernames zu finden
	 * Username ist immer in der DB vorhanden, da ansonst eine AuthenticationException geworfen wird
	 */
	private User findByName(String username) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.where(user.get(User_.username).in(username));

		TypedQuery<User> tq = entityManager.createQuery(cq);
		
		return tq.getSingleResult();
	}
}
