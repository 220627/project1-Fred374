package com.revature.services;

import com.revature.daos.AuthDAO;

public class AuthService {
	
	private static final AuthDAO AUTH_DAO = new AuthDAO();
	
	public int login(String username, String password) {
		
		if (AUTH_DAO.login(username,  password) == 1) {
			return 1;
		} else if (AUTH_DAO.login(username,  password) == 2) {
			return 2;
		} else {
			return 0;
		}
	}
}
