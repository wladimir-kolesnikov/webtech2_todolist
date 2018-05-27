package de.wt2.todo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name="permission")
@Table(name="permission")
public class Permission extends BaseEntity {
	
	private static final long serialVersionUID = -1572920557494497733L;

	@Column(name="permission_name")
	// Permission name, z.B. "EDIT, DELETE.." usw.
	private String permissionName;
	
	@ManyToMany(fetch=FetchType.EAGER,  mappedBy="permissions")
	// Die den Permissions zugeteilten roles. Mehrere Permissions können mehreren Roles zugeteilt sein
	private Set<Role> roles;

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
