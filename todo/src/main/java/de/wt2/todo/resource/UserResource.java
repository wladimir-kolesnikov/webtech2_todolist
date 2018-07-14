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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userID}/notes")
    public Note create(final Note t, @PathParam("userID") long userID) {
        entityManager.persist(t);
        User user = entityManager.find(User.class, userID);
        user.getNotes().add(t);
        entityManager.merge(user);
        return t;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userID}/notes/{noteID}")
    public Note update(final Note t) {
        entityManager.merge(t);
        return t;
    }
}
