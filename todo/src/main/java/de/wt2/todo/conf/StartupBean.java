package de.wt2.todo.conf;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import de.wt2.todo.entity.User;

@Singleton
@Startup
@Transactional
public class StartupBean {
	
	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct	
	public void startup() {
		createUsers();
	}
	
	private void createUsers() {
		User user1 = new User();
		user1.setUsername("Karsten");
		user1.setJoined(new Date());
		
		User user2 = new User();
		user2.setUsername("Student");
		user2.setJoined(new Date());
		
		User user3 = new User();
		user3.setUsername("Student 2");
		user3.setJoined(new Date());
		
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(user3);
	}

	@PreDestroy
	public void shutdown() {
		// potential cleanup work
	}

}
