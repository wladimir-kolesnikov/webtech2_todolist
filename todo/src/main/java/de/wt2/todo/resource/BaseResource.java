package de.wt2.todo.resource;

import java.util.List;

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

//Klasse ist momentan nur zum testen da

public abstract class BaseResource<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public abstract T find(@PathParam("id") long id);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public abstract List<T> findAll();
	
	
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
