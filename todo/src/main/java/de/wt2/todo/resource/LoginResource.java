package de.wt2.todo.resource;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@Transactional
@Stateless
@Path("/login")
public class LoginResource {
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public boolean login(@FormParam("username") String username, @FormParam("password") String password) {
		System.out.println("USERNAME: " + username);
		System.out.println("PASSWORD: " + password);
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated() ) {
		    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		    token.setRememberMe(true);
		    
		    try {
			    currentUser.login(token);
			    return true;
		    } catch (AuthenticationException ae) {
		    	ae.printStackTrace();
		    	return false;
		    }
		} else {
			return true;
		}
	}
}
