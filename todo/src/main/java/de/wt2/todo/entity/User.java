package de.wt2.todo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name="user")
@Table(name="user")
public class User extends BaseEntity {

	@Column(unique=true)
	private String username;
	private Date joined;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="user_role",
			joinColumns= {@JoinColumn(name="user_name", referencedColumnName="username")},
			inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}	
	)
	private Set<Role> roles;

	private static final long serialVersionUID = -1938877027229076760L;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
