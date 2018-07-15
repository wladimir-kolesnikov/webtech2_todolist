package de.wt2.todo.conf;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import de.wt2.todo.entity.Note;
import de.wt2.todo.entity.Relevance;
//import de.wt2.todo.entity.Permission;
import de.wt2.todo.entity.Role;
import de.wt2.todo.entity.User;

/*
 * startup() Methode wird jedes Mal beim Starten und 
 * shutdown() Methode jedes mal beim Beenden des Servers ausgefÃ¼hrt 
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
	 * Kann/Muss verÃ¤ndert werden
	 */
	private void createUsers() {
		
		/*
		 * Passwort muss encrypted werden, da es shiro by default beim login so checkt und nicht als plain text
		 */
		PasswordService ps = new DefaultPasswordService();
		
		User user1 = new User();
		user1.setUsername("Student_1");
		user1.setJoined(LocalDate.now());
		String pass = ps.encryptPassword("test1");
		user1.setPassword(pass);
		
		//Set<Role> newRoles = new Set<Role>();
		user1.setRoles(new HashSet<Role>());
		user1.setNotes(new HashSet<Note>());
		
		User user2 = new User();
		user2.setUsername("Student_2");
		user2.setJoined(LocalDate.now());
		pass = ps.encryptPassword("test2");
		user2.setPassword(pass);
		
		user2.setRoles(new HashSet<Role>());
		user2.setNotes(new HashSet<Note>());
		
		User user3 = new User();
		user3.setUsername("Student_3");
		user3.setJoined(LocalDate.now());
		pass = ps.encryptPassword("test3");
		user3.setPassword(pass);
		
		user3.setRoles(new HashSet<Role>());
		user3.setNotes(new HashSet<Note>());
		
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(user3);
		
		Role adminRole = new Role();
		adminRole.setRoleName("admin");
		entityManager.persist(adminRole);
		
		Role userRole = new Role();
		userRole.setRoleName("user");
		entityManager.persist(userRole);
		
		Note note1 = new Note();
		note1.setAuthorUser(user1);
		note1.setAuthor(user1.getUsername());
		note1.setCreated(LocalDate.now());
		note1.setDue(LocalDate.now());
		note1.setHeadline("test");
		note1.setLastEdited(LocalDate.now());
		note1.setRelevance(Relevance.IMPORTANT);
		note1.setContent("TEST FOR NOTE1");
		entityManager.persist(note1);
		
		Note note2 = new Note();
		note2.setAuthorUser(user1);
		note2.setAuthor(user1.getUsername());
		note2.setCreated(LocalDate.now());
		note2.setDue(LocalDate.now());
		note2.setHeadline("test2");
		note2.setLastEdited(LocalDate.now());
		note2.setRelevance(Relevance.NORMAL);
		note2.setContent("TEST FOR NOTE2");
		entityManager.persist(note2);
		
		Note note3 = new Note();
		note3.setAuthorUser(user3);
		note3.setAuthor(user3.getUsername());
		note3.setCreated(LocalDate.now());
		note3.setDue(LocalDate.now());
		note3.setHeadline("test3");
		note3.setLastEdited(LocalDate.now());
		note3.setRelevance(Relevance.NORMAL);
		note3.setContent("TEST FOR NOTE3");
		entityManager.persist(note3);

		Set<Role> user1Roles = user1.getRoles();
		user1Roles.add(adminRole);
		user1.setRoles(user1Roles);
		
		Set<Role> user2Roles = user2.getRoles();
		user2Roles.add(userRole);
		user2.setRoles(user2Roles);
		
		Set<Role> user3Roles = user3.getRoles();
		user3Roles.add(userRole);
		user3.setRoles(user3Roles);
		
		Set<Note> userNotes = user1.getNotes();
		userNotes.add(note1);
		userNotes.add(note2);
	}
	

	@PreDestroy
	public void shutdown() {
		// potential cleanup work
	}

}

