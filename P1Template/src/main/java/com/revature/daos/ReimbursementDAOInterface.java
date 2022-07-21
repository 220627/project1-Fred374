package com.revature.daos;

import com.revature.models.Reimbursement;
import java.util.ArrayList;

public interface ReimbursementDAOInterface {
	
	boolean insertReimb(Reimbursement reimb);
	
	ArrayList<Reimbursement> getAllReimb();
	
	ArrayList<Reimbursement> getOpenReimb();
	
	ArrayList<Reimbursement> getReimbByUser(int user_id);
	
	boolean resolveReimb(int reimb_id, int res_id);
	
	Reimbursement getReimb(int reimb_id);

}
