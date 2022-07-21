package com.revature.daos;

import com.revature.models.ReimbType;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TypeDAO implements TypeDAOInterface{
	
	public ReimbType getType(int type_id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "SELECT * FROM ers_reimbursement_types WHERE reimb_id = ?";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, type_id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				ReimbType type = new ReimbType(
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_type")
						);
				return type;
			} else {
				System.out.println("---------- TYPE ID INVALID ----------");
			}
			
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT GET TYPE ----------");
			e.printStackTrace();
		}
		return null;
	}

}
