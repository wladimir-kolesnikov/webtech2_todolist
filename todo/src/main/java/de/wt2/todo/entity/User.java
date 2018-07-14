package de.wt2.todo.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name="user")
@Table(name="user")
public class User extends BaseEntity {

	private static final long serialVersionUID = -1938877027229076760L;

	@Column(unique=true)
	// Der username des Users
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	// Registrierungsdatum
	private LocalDate joined;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="user_role",
			joinColumns= {@JoinColumn(name="user_name", referencedColumnName="username")},
			inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}	
	)
	// Set von Roles, die dem User zugeordnet sind (z.B. Admin, S-Mod, Guest usw.)
	private Set<Role> roles;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="authorUser")
	// Alle Notes, die von dem User erstellt wurden
	private Set<Note> notes;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getJoined() {
		return joined;
	}

	public void setJoined(LocalDate joined) {
		this.joined = joined;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
