package com.revature.models;

public class ReimbType {
	
	private int type_id;
	private String type;
	
	public ReimbType(int type_id, String type) {
		super();
		this.type_id = type_id;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "ReimbType [type_id=" + type_id + ", type=" + type + "]";
	}
	
	public int getType_id() {
		return type_id;
	}
	
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
