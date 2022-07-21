package com.revature.controllers;

import com.revature.models.LoginDTO;
import com.revature.services.AuthService;
import com.revature.daos.AuthDAO;
import io.javalin.http.Handler;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;

public class AuthController {
	
	private static final AuthService AUTH_SERVICE = new AuthService();
	
	public static HttpSession ses;
	
	public Handler loginHandler = (ctx) -> {
		Gson gson = new Gson();
		String body = ctx.body();
		LoginDTO user = gson.fromJson(body, LoginDTO.class);
		String name = AUTH_SERVICE.login(user.getUsername(), user.getPassword());
		
		if (name != null) {
			ses = ctx.req.getSession();
			ctx.result(gson.toJson(AuthDAO.cur_user));
			ctx.status(202);
		} else {
			ctx.status(401);
		}
		
	};
	
	public Handler logoutHandler = (ctx) -> {
		if (AuthController.ses != null) {
			ses = null;
			ctx.result("Good Bye " + AuthDAO.cur_user.getFirst_name());
			AuthDAO.cur_user = null;
			ctx.status(202);
		} else {
			ctx.result("Not Currently Logged In");
			ctx.status(401);
		}
	};
}
