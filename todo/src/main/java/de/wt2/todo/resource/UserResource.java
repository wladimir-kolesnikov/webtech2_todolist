package de.wt2.todo.resource;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Path;

import de.wt2.todo.entity.User;

// Klasse ist momentan nur zum testen da
@Transactional
@Stateless
@Path("user")
public class UserResource extends BaseResource<User> {

	@PersistenceContext
	private EntityManager entityManager;
	
	public User find(long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		CriteriaQuery query = entityManager.getCriteriaBuilder().createQuery();
		query.select(query.from(User.class));
		
		Query q = entityManager.createQuery(query);
		return q.getResultList();
	}
}
