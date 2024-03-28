package com.devcamp.thongnh.realestate.Model.User;
import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

	@Column(name="role_name")
	private String roleName;

	@Column(name="role_key")
	private String roleKey;

		@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
		@JoinTable(name = "role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
				@JoinColumn(name = "permission_id") })
		private Set<Permission> permissions = new HashSet<>();

	public Role() {
	}

	public Role(long id, boolean deleted, Date createdAt, Date updatedAt, Long createdBy, Long updatedBy,
			String roleName, String roleKey, Set<Permission> permissions) {
		super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
		this.roleName = roleName;
		this.roleKey = roleKey;
		this.permissions = permissions;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	

	
}
