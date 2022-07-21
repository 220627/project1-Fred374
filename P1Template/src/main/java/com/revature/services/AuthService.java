package com.revature.services;

import com.revature.daos.AuthDAO;

public class AuthService {
	
	private static final AuthDAO AUTH_DAO = new AuthDAO();
	
	public String login(String username, String password) {
		
		if (AUTH_DAO.login(username,  password)) {
			return AuthDAO.cur_user.getFirst_name();
		} else {
			return null;
		}
	}
}
