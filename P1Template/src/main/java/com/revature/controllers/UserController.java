package com.revature.controllers;

import com.revature.daos.UserDAO;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class UserController {
	
	private static final UserDAO USER_DAO = new UserDAO();
	
	public static Logger log = LogManager.getLogger();
	
	public Handler getUserHandler = (ctx) -> {
		if (AuthController.ses != null) {
			log.info("User Fetched User Data");
			Gson gson = new Gson();
			int user_id = Integer.parseInt(ctx.pathParam("user_id"));
			String body = gson.toJson(USER_DAO.getUser(user_id));
			ctx.result(body);
			ctx.status(200);
		} else {
			log.info("User Failed To Fetch User Data");
			ctx.result("Please Log In");
			ctx.status(401);
		}
		
	};

}