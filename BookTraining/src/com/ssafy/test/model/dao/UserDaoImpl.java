package com.ssafy.test.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.test.model.dto.User;
import com.ssafy.test.util.DBUtil;

public class UserDaoImpl implements UserDao {
	private DBUtil util = DBUtil.getUtil();
	
	private static UserDao instance = new UserDaoImpl();
	
	private UserDaoImpl() {};
	public static UserDao getInstance() {
		return instance;
	}
	
	@Override
	public User select(String id, String pass) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user where id=?";
			con = util.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				return new User(id,
							rs.getString("pass"),
							rs.getString("name")
						);
			}
		} finally {
			util.close(con,stmt,rs);
		}
		// user가 없으면 null 리턴
		return null;
	}
	
	public static void main(String[] args) {
		try {
			UserDao dao = UserDaoImpl.getInstance();
			System.out.println(dao.select("ssafy", "1234"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
