package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.daos.AuthDAO;
import com.revature.daos.ReimbursementDAO;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;

public class ReimbursementController {
	
	private static final ReimbursementDAO REIMB_DAO = new ReimbursementDAO();
	
	public static Logger log = LogManager.getLogger();
	
	public Handler insertReimbHanlder = (ctx) -> {
		Gson gson = new Gson();
		String body = ctx.body();
		Reimbursement reimb = gson.fromJson(body, Reimbursement.class);
		System.out.println(body);
		System.out.println(reimb);
		if (REIMB_DAO.insertReimb(reimb)) {
			log.info("User Added New Reimbursement");
			System.out.println(reimb + "\n---------- ADDED SUCCESSFULLY ----------");
			ctx.status(202);
		} else {
			log.info("User Failed To Add New Reimbursement");
			ctx.status(406);
		}
	};
	
	public Handler getAllReimbHandler = (ctx) -> {
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			log.info("User Retrieved All Reimbursements");
			Gson gson = new Gson();
			String body = gson.toJson(REIMB_DAO.getAllReimb());
			ctx.result(body);
			ctx.status(200);
		} else {
			log.info("User Failed To Retrieve All Reimbursements");
			ctx.result("Please Log In As Admin");
			ctx.status(401);
		}
	};
	
	public Handler getOpenReimbHandler = (ctx) -> {
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			log.info("User Retrieved All Open Reimbursements");
			Gson gson = new Gson();
			String body = gson.toJson(REIMB_DAO.getOpenReimb());
			ctx.result(body);
			ctx.status(200);
		} else {
			log.info("User Failed To Retrieve All Open Reimbursements");
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler getReimbByUserAdminHandler = (ctx) -> {
		int user_id = Integer.parseInt(ctx.pathParam("user_id"));
		
		if (AuthController.ses != null && (AuthDAO.cur_user.getRole().getRole_id() == 1 || AuthDAO.cur_user.getUser_id() == user_id)) {
			Gson gson = new Gson();
			String body = gson.toJson(REIMB_DAO.getReimbByUser(user_id));
			log.info("User Retrieved All Reimbursements for User ID " + user_id);
			ctx.result(body);
			ctx.status(200);
		} else {
			log.info("User Failed To Retrieve All Reimbursements for User ID " + user_id);
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler getReimbByUserCurHandler = (ctx) -> {
		if (AuthController.ses != null) {
			log.info("User Retrieved All Their Reimbursements");
			Gson gson = new Gson();
			String body = gson.toJson(REIMB_DAO.getReimbByUser(AuthDAO.cur_user.getUser_id()));
			ctx.result(body);
			ctx.status(200);
		} else {
			log.info("User Failed To Retrieve All Their Reimbursements");
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler resolveReimbHandler = (ctx) -> {
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			int reimb_id = Integer.parseInt(ctx.pathParam("reimb_id"));
			int res_id = Integer.parseInt(ctx.body());
			
			if (REIMB_DAO.resolveReimb(reimb_id, res_id)) {
				log.info("User Resolved Reimbursement Number " + reimb_id);
				ctx.result("Reimbursement " + reimb_id + " has been resolved");
				ctx.status(200);
			} else {
				log.info("User Failed To Resolve Reimbursement Number " + reimb_id);
				ctx.result("Failed to resolve reimbursement " + reimb_id);
				ctx.status(400);
			}
		} else {
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};
	
	public Handler getReimbHandler = (ctx) -> {
		int reimb_id = Integer.parseInt(ctx.pathParam("reimb_id"));
		
		if (AuthController.ses != null && AuthDAO.cur_user.getRole().getRole_id() == 1) {
			Gson gson = new Gson();
			String body = gson.toJson(REIMB_DAO.getReimb(reimb_id));
			log.info("User Retrieved Reimbursement Number " + reimb_id);
			ctx.result(body);
			ctx.status(200);
		} else {
			log.info("User Failed To Retrieve Reimbursement Number " + reimb_id);
			ctx.result("Please Log In");
			ctx.status(401);
		}
	};

}