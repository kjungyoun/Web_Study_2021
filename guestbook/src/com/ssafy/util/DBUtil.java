package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String ID = "ssafy";
	private static final String PASSWORD = "ssafy";
	
	// 최초 class loading 시 단 한번만 load됨 -> 또 다른 싱글톤 패턴 구현 방법
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
	}
	
	public static Connection getConnect() throws SQLException {
		return DriverManager.getConnection(URL,ID,PASSWORD);
	}
	
//	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
//		try {
//			if(rs != null)
//				rs.close();
//			if(pstmt != null)
//				pstmt.close();
//			if(conn != null)
//				conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void close(PreparedStatement pstmt, Connection conn) {
//		try {
//			if(pstmt != null)
//				pstmt.close();
//			if(conn != null)
//				conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void close(AutoCloseable... closeables) {
		try {
				for (AutoCloseable ac : closeables) {
					if(ac != null)
					ac.close();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

