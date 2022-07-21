package com.revature.daos;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO implements RoleDAOInterface {

	public Role getRole(int role_id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "SELECT * FROM ers_user_roles WHERE user_role_id = ?";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, role_id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				Role role = new Role(
						rs.getInt("user_role_id"),
						rs.getString("user_role")
						);
				return role;
			} else {
				System.out.println("---------- ROLE ID INVALID ----------");
			}
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT GET ROLE ----------");
			e.printStackTrace();
		}
		return null;
	}
}
