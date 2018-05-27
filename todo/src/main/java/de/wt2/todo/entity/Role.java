package de.wt2.todo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name="role")
@Table(name="role")
public class Role extends BaseEntity {
	
	private static final long serialVersionUID = -3626845496890678650L;

	@Column(name="role_name") 
	// Name der Role (z.B. Admin, S-Mod etc.)
	private String roleName;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="role_permission",
			joinColumns= {@JoinColumn(name="role_name", referencedColumnName="role_name")},
			inverseJoinColumns = {@JoinColumn(name="permission_id", referencedColumnName="id")}	
	)
	/*
	 * Explizite Permissions, z.b. "EDIT, DELETE..." usw.
	 * Damit die Beziehung nur in 1 Table gespeichert wird, wird JoinTable verwendet
	 * In der DB Tabelle wird "id" zu "permission_id"
	 */
	private Set<Permission> permissions;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="roles")
	// Die den Roles zugeteilten User. Mehrere Roles können mehrere User beinhalten
	private Set<User> users;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}