package com.revature.daos;

import com.revature.models.Resolution;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResolutionDAO implements ResolutionDAOInterface {
	
	public Resolution getResolution(int res_id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "SELECT * FROM ers_reimbursement_resolutions WHERE reimb_res_id = ?";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, res_id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				Resolution res = new Resolution(
						rs.getInt("reimb_res_id"),
						rs.getString("reimb_res")
						);
				return res;
			} else {
				System.out.println("---------- RES ID INVALID ----------");
			}
			
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT GET RESOLUTION ----------");
			e.printStackTrace();
		}
		return null;
	}

}
