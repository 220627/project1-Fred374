package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.daos.AuthDAO;
import com.revature.daos.ReimbursementDAO;
import io.javalin.http.Handler;
import com.google.gson.Gson;

public class ReimbursementController {
	
	private static final ReimbursementDAO REIMB_DAO = new ReimbursementDAO();
	
	public Handler insertReimbHanlder = (ctx) -> {
		Gson gson = new Gson();
		String body = ctx.body();
		Reimbursement reimb = gson.fromJson(body, Reimbursement.class);
		
		if (REIMB_DAO.insertReimb(reimb)) {
			System.out.println(reimb + "\n---------- ADDED SUCCESSFULLY ----------");
			ctx.status(202);
		} else {
			ctx.status(406);
		}
	};
	
	public Handler getAllReimbHandler = (ctx) -> {
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			Gson gson = new Gson();
			String body = gson.toJson(REIMB_DAO.getAllReimb());
			ctx.result(body);
			ctx.status(200);
		} else {
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler getOpenReimbHandler = (ctx) -> {
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			Gson gson = new Gson();
			String body = gson.toJson(REIMB_DAO.getOpenReimb());
			ctx.result(body);
			ctx.status(200);
		} else {
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler getReimbByUserAdminHandler = (ctx) -> {
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			Gson gson = new Gson();
			int user_id = Integer.parseInt(ctx.pathParam("user_id"));
			String body = gson.toJson(REIMB_DAO.getReimbByUser(user_id));
			ctx.result(body);
			ctx.status(200);
		} else {
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler getReimbByUserCurHandler = (ctx) -> {
		if (AuthController.ses != null) {
			Gson gson = new Gson();
			String body = gson.toJson(REIMB_DAO.getReimbByUser(AuthDAO.cur_user.getUser_id()));
			ctx.result(body);
			ctx.status(200);
		} else {
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler resolveReimbHandler = (ctx) -> {
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			int reimb_id = Integer.parseInt(ctx.pathParam("reimb_id"));
			int res_id = Integer.parseInt(ctx.body());
			
			if (REIMB_DAO.resolveReimb(reimb_id, res_id)) {
				ctx.result("Reimbursement " + reimb_id + " has been resolved");
				ctx.status(200);
			} else {
				ctx.result("Failed to resolve reimbursement " + reimb_id);
				ctx.status(400);
			}
		} else {
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler getReimbHandler = (ctx) -> {
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			Gson gson = new Gson();
			int reimb_id = Integer.parseInt(ctx.pathParam("reimb_id"));
			String body = gson.toJson(REIMB_DAO.getReimb(reimb_id));
			ctx.result(body);
			ctx.status(200);
		} else {
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};

}