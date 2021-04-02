package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.model.dto.MemberDto;
import com.ssafy.util.DBUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public MemberDto login(String userid, String userpwd)throws SQLException {
		MemberDto member = new MemberDto();
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from ssafy_member \n");
			sql.append("where userid = ? and userpwd = ? \n");
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member.setUserid(userid);
				member.setUserpwd(userpwd);
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setUsername(rs.getString("username"));
				member.setJoindate(rs.getString("joindate"));
			}	
		}finally {
			DBUtil.close(rs,pstmt,conn);
		}
		return member;
	}
	
	public static void main(String[] args) throws SQLException {
		// 테스트용 메서드
		LoginDaoImpl dao = new LoginDaoImpl();
		MemberDto member = dao.login("ssafy", "ssafy");
		System.out.println(member);
	}

}
