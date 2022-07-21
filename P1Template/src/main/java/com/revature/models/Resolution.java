package com.revature.models;

public class Resolution {

	private int res_id;
	private String res;
	
	public Resolution(int res_id, String res) {
		super();
		this.res_id = res_id;
		this.res = res;
	}
	public Resolution(String res) {
		super();
		this.res = res;
	}
	@Override
	public String toString() {
		return "Resolution [res_id=" + res_id + ", res=" + res + "]";
	}
	public int getRes_id() {
		return res_id;
	}
	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	
}
