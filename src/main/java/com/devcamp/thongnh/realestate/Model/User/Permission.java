package com.devcamp.thongnh.realestate.Model.User;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {

	@Column(name = "permission_key")
	private String permissionKey;

	@Column(name = "permission_name")
	private String permissionName;

	public Permission() {
	}

	public Permission(long id, boolean deleted, Date createdAt, Date updatedAt, Long createdBy, Long updatedBy,
			String permissionKey, String permissionName) {
		super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
		this.permissionKey = permissionKey;
		this.permissionName = permissionName;
	}

	public String getPermissionKey() {
		return permissionKey;
	}

	public void setPermissionKey(String permissionKey) {
		this.permissionKey = permissionKey;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
		
}
