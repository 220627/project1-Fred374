package com.revature.daos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDAO implements AuthDAOInterface {
	
	public static User cur_user;
	
	public int login(String username, String password) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String str_sql = "SELECT * FROM ers_users WHERE users_username = ? AND users_password = ?";
			PreparedStatement stmt = conn.prepareStatement(str_sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				cur_user = new User(
						rs.getInt("users_id"),
						rs.getString("users_username"),
						rs.getString("users_first_name"),
						rs.getString("users_last_name"),
						rs.getString("users_email"),
						rs.getInt("users_role_fk")
						);
				return 1;
			}
			
		}catch (SQLException e) {
			System.out.println("---------- CONNECTION FAILURE AT LOGIN ----------");
			e.printStackTrace();
			return 2;
		}
		return 0;
	}

}
