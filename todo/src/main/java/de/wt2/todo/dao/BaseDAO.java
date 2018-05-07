package de.wt2.todo.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDAO<T> {
	
	/*
	 * This abstract class contains the basic functions to interact with the DB (CRUD = create, update, delete)
	 * and two abstract methods getAll() and findById which need to be implemented differently because
	 * of the different SQL Queries
	 */
	
    @PersistenceContext
    private EntityManager entityManager;
    
    protected abstract Set<T> getAll();
    protected abstract T findById(long id);
    
    protected void create(T t) {
    	entityManager.persist(t);
    }
    
    protected void update(T t) {
    	entityManager.merge(t);
    }
    
    protected void delete(T t) {
    	entityManager.remove(t);
    }
}
