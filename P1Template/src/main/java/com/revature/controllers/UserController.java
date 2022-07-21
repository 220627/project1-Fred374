package com.revature.controllers;

import com.revature.daos.UserDAO;
import io.javalin.http.Handler;
import com.google.gson.Gson;

public class UserController {
	
	private static final UserDAO USER_DAO = new UserDAO();
	
	public Handler getUserHandler = (ctx) -> {
		if (AuthController.ses != null) {
			Gson gson = new Gson();
			int user_id = Integer.parseInt(ctx.pathParam("user_id"));
			String body = gson.toJson(USER_DAO.getUser(user_id));
			ctx.result(body);
			ctx.status(200);
		} else {
			ctx.result("Please Log In");
			ctx.status(401);
		}
		
	};

}