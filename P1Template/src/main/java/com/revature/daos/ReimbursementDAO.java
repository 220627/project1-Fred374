package com.revature.daos;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class ReimbursementDAO implements ReimbursementDAOInterface {
	
	public boolean insertReimb(Reimbursement reimb) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "INSERT INTO ers_reimbursements(reimb_amount, reimb_submitted, reimb_description,"
					+ " users_author_fk, reimb_status_fk, reimb_type_fk, reimb_receipt) VALUES (?, current_timestamp, ?, ?, 1, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, reimb.getInt_amount());
			stmt.setString(2, reimb.getStr_description());
			stmt.setInt(3, reimb.getInt_author_id());
			stmt.setInt(4, reimb.getInt_type_id());
			stmt.setBytes(5, reimb.getReceipt());
			stmt.executeUpdate();
			
			System.out.println("---------- REIMB WAS INSERTED ---------");
			return true;
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT REIMB INSERT ----------");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ArrayList<Reimbursement> getAllReimb() {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			ArrayList<Reimbursement> list_reimb = new ArrayList<>();
			Statement stmt = conn.createStatement();
			String str_sql = "SELECT * FROM ers_reimbursements ORDER BY reimb_submitted;";
			ResultSet rs = stmt.executeQuery(str_sql);
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("users_author_fk"),
						rs.getInt("users_resolver_fk"),
						rs.getInt("reimb_status_fk"),
						rs.getInt("reimb_type_fk"),
						rs.getInt("reimb_resolution")
						);
				list_reimb.add(reimb);
			}
			return list_reimb;
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT REIMB GET ALL ----------");
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Reimbursement> getOpenReimb() {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			ArrayList<Reimbursement> list_reimb = new ArrayList<>();
			Statement stmt = conn.createStatement();
			String str_sql = "SELECT * FROM ers_reimbursements WHERE reimb_resolved IS NULL ORDER BY reimb_submitted;";
			ResultSet rs = stmt.executeQuery(str_sql);
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("users_author_fk"),
						rs.getInt("users_resolver_fk"),
						rs.getInt("reimb_status_fk"),
						rs.getInt("reimb_type_fk"),
						rs.getInt("reimb_resolution")
						);
				list_reimb.add(reimb);
			}
			return list_reimb;
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT REIMB GET ALL ----------");
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Reimbursement> getReimbByUser(int user_id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			ArrayList<Reimbursement> list_reimb = new ArrayList<>();
			String str_sql = "SELECT * FROM ers_reimbursements WHERE users_author_fk = ? ORDER BY reimb_submitted;";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("users_author_fk"),
						rs.getInt("users_resolver_fk"),
						rs.getInt("reimb_status_fk"),
						rs.getInt("reimb_type_fk"),
						rs.getInt("reimb_resolution")
						);
				list_reimb.add(reimb);
			}
			return list_reimb;
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT REIMB GET BY USER ----------");
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean resolveReimb(int reimb_id, int res_id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "UPDATE ers_reimbursements SET reimb_resolved = current_timestamp, "
					+ "users_resolver_fk = ?, reimb_resolution = ? WHERE reimb_id = ?";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, AuthDAO.cur_user.getUser_id());
			stmt.setInt(2, res_id);
			stmt.setInt(3, reimb_id);
			stmt.executeUpdate();
			
			System.out.println("---------- REIMB WAS RESOLVED ----------");
			return true;
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT REIMB RESOLVE ----------");
			e.printStackTrace();
			return false;
		}
	}
	
	public Reimbursement getReimb(int reimb_id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "SELECT * FROM ers_reimbursements WHERE reimb_id = ?";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, reimb_id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Reimbursement reimb = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("users_author_fk"),
						rs.getInt("users_resolver_fk"),
						rs.getInt("reimb_status_fk"),
						rs.getInt("reimb_type_fk"),
						rs.getInt("reimb_resolution")
						);
				return reimb;
			} else {
				System.out.println("---------- REIMB ID INVALID ----------");
			}
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT REIMB GET BY ID ----------");
			e.printStackTrace();
		}
		return null;
	}
	
}
