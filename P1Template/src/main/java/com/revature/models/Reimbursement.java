package com.revature.models;

import java.sql.Timestamp;
import com.revature.daos.UserDAO;

public class Reimbursement {
	
	private int int_reimb_id;
	private int int_amount;
	private Timestamp time_submitted;
	private Timestamp time_resolved;
	private String str_description;
	private User user_author;
	private User user_resolver;
	private int int_status_id;
	private int int_type_id;
	private int int_res_id;
	private String receipt;
	
	public Reimbursement(int int_reimb_id, int int_amount, Timestamp time_submitted, Timestamp time_resolved,
			String str_description, int int_author_id, int int_resolver_id, int int_status_id, int int_type_id,
			int int_res_id) {
		super();
		UserDAO uDAO = new UserDAO();
		this.int_reimb_id = int_reimb_id;
		this.int_amount = int_amount;
		this.time_submitted = time_submitted;
		this.time_resolved = time_resolved;
		this.str_description = str_description;
		this.user_author = uDAO.getUser(int_author_id);
		if (int_resolver_id > 0) {
			this.user_resolver = uDAO.getUser(int_resolver_id);
		}
		this.int_status_id = int_status_id;
		this.int_type_id = int_type_id;
		this.int_res_id = int_res_id;
	}

	public Reimbursement(int int_amount, Timestamp time_submitted, Timestamp time_resolved, String str_description,
			int int_author_id, int int_resolver_id, int int_status_id, int int_type_id, int int_res_id) {
		super();
		UserDAO uDAO = new UserDAO();
		this.int_amount = int_amount;
		this.time_submitted = time_submitted;
		this.time_resolved = time_resolved;
		this.str_description = str_description;
		this.user_author = uDAO.getUser(int_author_id);
		this.user_resolver = uDAO.getUser(int_resolver_id);
		this.int_status_id = int_status_id;
		this.int_type_id = int_type_id;
		this.int_res_id = int_res_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [int_reimb_id=" + int_reimb_id + ", int_amount=" + int_amount + ", time_submitted="
				+ time_submitted + ", time_resolved=" + time_resolved + ", str_description=" + str_description
				+ ", user_author=" + user_author + ", user_resolver=" + user_resolver + ", int_status_id="
				+ int_status_id + ", int_type_id=" + int_type_id + ", int_res_id=" + int_res_id + ", receipt="
				+ receipt + "]";
	}

	public int getInt_reimb_id() {
		return int_reimb_id;
	}

	public void setInt_reimb_id(int int_reimb_id) {
		this.int_reimb_id = int_reimb_id;
	}

	public int getInt_amount() {
		return int_amount;
	}

	public void setInt_amount(int int_amount) {
		this.int_amount = int_amount;
	}

	public Timestamp getTime_submitted() {
		return time_submitted;
	}

	public void setTime_submitted(Timestamp time_submitted) {
		this.time_submitted = time_submitted;
	}

	public Timestamp getTime_resolved() {
		return time_resolved;
	}

	public void setTime_resolved(Timestamp time_resolved) {
		this.time_resolved = time_resolved;
	}

	public String getStr_description() {
		return str_description;
	}

	public void setStr_description(String str_description) {
		this.str_description = str_description;
	}

	public User getUser_author() {
		return user_author;
	}

	public void setInt_author_id(int int_author_id) {
		UserDAO uDAO = new UserDAO();
		this.user_author = uDAO.getUser(int_author_id);
	}
	
	public int getInt_author_id() {
		return user_author.getUser_id();
	}

	public User getUser_resolver() {
		return user_resolver;
	}

	public void setInt_resolver_id(int int_resolver_id) {
		UserDAO uDAO = new UserDAO();
		this.user_resolver = uDAO.getUser(int_resolver_id);
	}
	
	public int getInt_resolver_id() {
		return user_resolver.getUser_id();
	}

	public int getInt_status_id() {
		return int_status_id;
	}

	public void setInt_status_id(int int_status_id) {
		this.int_status_id = int_status_id;
	}

	public int getInt_type_id() {
		return int_type_id;
	}

	public void setInt_type_id(int int_type_id) {
		this.int_type_id = int_type_id;
	}

	public int getInt_res_id() {
		return int_res_id;
	}

	public void setInt_res_id(int int_res_id) {
		this.int_res_id = int_res_id;
	}

	public byte[] getReceipt() {
		return receipt.getBytes();
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt.toString();
	}

	public void setUser_author(User user_author) {
		this.user_author = user_author;
	}

	public void setUser_resolver(User user_resolver) {
		this.user_resolver = user_resolver;
	}
	
	

}
