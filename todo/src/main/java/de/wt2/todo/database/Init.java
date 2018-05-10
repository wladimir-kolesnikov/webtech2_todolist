package de.wt2.todo.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import de.wt2.todo.entity.User;

public class Init implements ServletContextListener{

	
	private EntityManagerFactory entityManagerFactory;
	@PersistenceContext
	private EntityManager entityManager;
	
	public Init() {

	}
	
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("THIS IS JUST A TEST!!!!");

		entityManagerFactory = Persistence.createEntityManagerFactory("de.wt2.todo");
		this.entityManager = entityManagerFactory.createEntityManager();
		
		User user = new User();
		user.setUsername("chillivanilli");
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		
	}

	public void contextDestroyed(ServletContextEvent sce) { 
	    entityManager.close();
	}
}
