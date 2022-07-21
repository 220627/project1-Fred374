package com.revature;

import com.revature.utils.ConnectionUtil;
import com.revature.controllers.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import io.javalin.Javalin;
import java.time.LocalTime;

public class Launcher extends Thread{

	public static void main(String[] args) {
		
		//Welcome to P1! 
		
		//If you're reading this, you've successfully cloned your repo and imported the template
		
		//Do you coding in this project, and don't forget to save/push your progress with:
		//git add.
		//git commit -m"message"
		//git push
		
		//yes, you WILL need to push to your repo. The clients will want to see your project repos in your portfolios.
		
		//here's a dog to help you on your way. Have fun!
		if (args.length != 0) {
			Launcher thread = new Launcher();
			thread.start();
		}
		System.out.println("                        __");
		System.out.println("                   (___()'`;");
		System.out.println("                   /,    /`");
		System.out.println("                   \\\\\"--\\\\");
		System.out.println();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("----------- Connection Successful -----------");
		} catch (SQLException e) {
			System.out.println("------------- Connection Failed -------------");
			e.printStackTrace();
		}
		
		Javalin app = Javalin.create(
				config -> { config.enableCorsForAllOrigins(); }
				).start(3001);
		
		ReimbursementController reimbControl = new ReimbursementController();
		AuthController authControl = new AuthController();
		UserController userControl = new UserController();
		
		app.post("/login", authControl.loginHandler);
		app.post("/logout", authControl.logoutHandler);
		app.get("/all_reimbursements", reimbControl.getAllReimbHandler);
		app.get("/open_reimbursements", reimbControl.getOpenReimbHandler);
		app.get("/user_reimbursements", reimbControl.getReimbByUserCurHandler);
		app.get("/user_reimbursements/:user_id", reimbControl.getReimbByUserAdminHandler);
		app.get("/user_reimbursements/:reimb_id", reimbControl.getReimbHandler);
		app.post("/insert_reimbursement", reimbControl.insertReimbHanlder);
		app.post("/resolve_reimbursement/:reimb_id", reimbControl.resolveReimbHandler);
		app.get("/get_user/:user_id", userControl.getUserHandler);
		
	}
	
	public void run() {
		String doggo1 = "     __\n";
		String doggo2 = "(___()'`;\n";
		String doggo3 = "/,    /`\n";
		String doggo4 = "\\\\\"--\\\\\n\n";
		int count = 0;
		while (count < 20) {
			if (LocalTime.now().getSecond() % 1 == 0) {
				try {
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				doggo1 = " " + doggo1;doggo2 = " " + doggo2;doggo3 = " " + doggo3;doggo4 = " " + doggo4;
				String doggo = doggo1+doggo2+doggo3+doggo4;
				System.out.println(doggo);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
			}
		}
		run();
	}
	
}
