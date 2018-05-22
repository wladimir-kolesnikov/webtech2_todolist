package de.wt2.todo.resource;


import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Path;

import de.wt2.todo.entity.User;

@Transactional
@Stateless
@Path("user")
public class UserResource extends BaseResource<User> {

	@PersistenceContext
	private EntityManager entityManager;
	

	public User get(long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
