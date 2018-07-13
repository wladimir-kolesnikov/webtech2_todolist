package de.wt2.todo.conf;

import java.time.LocalTime;
import java.util.Date;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;



//import de.wt2.todo.entity.Permission;
import de.wt2.todo.entity.Role;
import de.wt2.todo.entity.User;
import de.wt2.todo.entity.Note;
import de.wt2.todo.entity.Relevance;

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
//		User user1 = new User();
//		user1.setUsername("Student_1");
//		user1.setJoined(new Date());
//		user1.setPassword("test1");
//		//Set<Role> newRoles = new Set<Role>();
//		user1.setRoles(new HashSet<Role>());
//		user1.setNotes(new HashSet<Note>());
//		
//		User user2 = new User();
//		user2.setUsername("Student_2");
//		user2.setJoined(new Date());
//		user2.setPassword("test2");
//
//		User user3 = new User();
//		user3.setUsername("Student_3");
//		user3.setJoined(new Date());
//		user3.setPassword("test3");
//
//		entityManager.persist(user1);
//		entityManager.persist(user2);
//		entityManager.persist(user3);
//		
//		Role adminRole = new Role();
//		adminRole.setRoleName("admin");
//		HashSet<User> users = new HashSet<User>();
//		entityManager.persist(adminRole);
//		
//		
//		Note note1 = new Note();
//		note1.setAuthor(user1.getUsername());
//		note1.setCreated(new Date());
//		note1.setDue(new Date());
//		note1.setHeadline("test");
//		note1.setLastEdited(new Date());
//		note1.setRelevance(Relevance.IMPORTANT);
//		entityManager.persist(note1);
//		
//		Note note2 = new Note();
//		note2.setAuthor(user1.getUsername());
//		note2.setCreated(new Date());
//		note2.setDue(new Date());
//		note2.setHeadline("test2");
//		note2.setLastEdited(new Date());
//		note2.setRelevance(Relevance.NORMAL);
//		entityManager.persist(note2);
//		
//		/*
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
//		*/
////		
////		Set<Permission> adminPermissions = adminRole.getPermissions();
////		adminPermissions.add(edit);
////		adminPermissions.add(delete);
////		adminPermissions.add(view);
////		
////		adminRole.setPermissions(adminPermissions);
////		entityManager.persist(adminPermissions);
////		entityManager.persist(adminRole);
////		
//		Set<Role> adminRoles = user1.getRoles();
//		adminRoles.add(adminRole);
////		
//		user1.setRoles(adminRoles);
//		
//		Set<Note> userNotes = user1.getNotes();
//		userNotes.add(note1);
//		userNotes.add(note2);
//		//entityManager.merge(user1);
	}
	

	@PreDestroy
	public void shutdown() {
		// potential cleanup work
	}

}
