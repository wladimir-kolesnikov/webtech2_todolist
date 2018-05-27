package de.wt2.todo.conf;

import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import de.wt2.todo.entity.Permission;
import de.wt2.todo.entity.Role;
import de.wt2.todo.entity.User;

/*
 * startup() Methode wird jedes Mal beim Starten und 
 * shutdown() Methode jedes mal beim Beenden des Servers ausgeführt 
 *
 */
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
	
	/*
	 * Erstellt zum Testen User in der Datenbank
	 * Kann/Muss verändert werden
	 */
	private void createUsers() {
		User user1 = new User();
		user1.setUsername("Student_1");
		user1.setJoined(new Date());
		user1.setPassword("test1");
		
		User user2 = new User();
		user2.setUsername("Student_2");
		user2.setJoined(new Date());
		user2.setPassword("test2");

		User user3 = new User();
		user3.setUsername("Student_3");
		user3.setJoined(new Date());
		user3.setPassword("test3");

		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(user3);
		
//		Role adminRole = new Role();
//		adminRole.setRoleName("admin");
//		entityManager.persist(adminRole);
//		
//		Permission edit = new Permission();
//		edit.setPermissionName("EDIT");
//		entityManager.persist(edit);
//		
//		Permission delete = new Permission();
//		delete.setPermissionName("DELETE");
//		entityManager.persist(delete);
//
//		Permission view = new Permission();
//		view.setPermissionName("VIEW");
//		entityManager.persist(view);
//		
//		Set<Permission> adminPermissions = adminRole.getPermissions();
//		adminPermissions.add(edit);
//		adminPermissions.add(delete);
//		adminPermissions.add(view);
//		
//		adminRole.setPermissions(adminPermissions);
//		entityManager.persist(adminPermissions);
//		
//		Set<Role> adminRoles = user1.getRoles();
//		adminRoles.add(adminRole);
//		
//		user1.setRoles(adminRoles);
//		entityManager.persist(user1);
//		
	}
	

	@PreDestroy
	public void shutdown() {
		// potential cleanup work
	}

}
