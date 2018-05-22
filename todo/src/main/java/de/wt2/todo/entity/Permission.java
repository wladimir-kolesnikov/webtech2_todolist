package de.wt2.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="permission")
@Table(name="permission")
public class Permission extends BaseEntity {
	
	private static final long serialVersionUID = -1572920557494497733L;

	@Column(name="permission_name")
	private String permissionName;
	
	
	// Relationship und fetchtype ergänzen
//	private Set<Role> roles;

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
}
