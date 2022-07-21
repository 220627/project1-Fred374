package com.revature.models;

public class Role {
	
	private int role_id;
	private String role;
	
	public Role(int role_id, String role) {
		super();
		this.role_id = role_id;
		this.role = role;
	}

	public Role(String role) {
		super();
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + "]";
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
