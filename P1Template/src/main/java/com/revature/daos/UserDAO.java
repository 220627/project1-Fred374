package com.revature.daos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements UserDAOInterface{

	public User getUser(int user_id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "SELECT * FROM ers_users WHERE users_id = ?";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User user = new User(
						rs.getInt("users_id"),
						rs.getString("users_username"),
						rs.getString("users_password"),
						rs.getString("users_first_name"),
						rs.getString("users_last_name"),
						rs.getString("users_email"),
						rs.getInt("users_role_fk")
						);
				return user;
			} else {
				System.out.println("---------- USER ID INVALID ----------");
			}
		} catch (SQLException e) {
			System.out.println("---------- FAILED AT GET USER ----------");
			e.printStackTrace();
		}
		return null;
	}
}
