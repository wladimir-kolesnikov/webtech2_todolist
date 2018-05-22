package de.wt2.todo.resource;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public abstract class BaseResource<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public abstract T get(@PathParam("id") long id);
	
	@GET
	public abstract Set<T> getAll();
	
	
	/*
	 * Basic CRUD operations
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T create(final T t) {
		entityManager.persist(t);
		return t;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T update(final T t) {
		entityManager.merge(t);
		return t;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T delete(final T t) {
		entityManager.remove(t);
		return t;
	}
}
