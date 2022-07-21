package com.revature.daos;

import com.revature.models.ReimbStatus;
import com.revature.utils.ConnectionUtil;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatusDAO implements StatusDAOInterface {

	public ReimbStatus getStatus(int status_id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "SELECT * FROM ers_reimbursement_statuses WHERE reimb_status_id = ?";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, status_id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				ReimbStatus status = new ReimbStatus(
						rs.getInt("reimb_status_id"),
						rs.getString("reimb_status")
						);
				return status;
			} else {
				System.out.println("---------- STATUS ID INVALID ----------");
			}
			
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT GET STATUS ----------");
			e.printStackTrace();
		}
		return null;
	}
	
}
