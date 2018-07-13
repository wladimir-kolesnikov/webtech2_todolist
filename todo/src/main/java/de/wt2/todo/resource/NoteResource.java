package de.wt2.todo.resource;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Path;

import de.wt2.todo.entity.Note;

@Transactional
@Stateless
@Path("/notes")
public class NoteResource extends BaseResource<Note> {

	public Note find(long id) {
		return entityManager.find(Note.class, id);
	}

	@Override
	public List<Note> findAll() {
		CriteriaQuery<Note> query = entityManager.getCriteriaBuilder().createQuery(Note.class);
		query.select(query.from(Note.class));
		
		Query q = entityManager.createQuery(query);
		return q.getResultList();
	}
}
