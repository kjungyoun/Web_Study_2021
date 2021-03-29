package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {
	
	public JdbcTest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩 성공!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JdbcTest jdbc = new JdbcTest();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafydb?erverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8", "ssafy", "ssafy");
			System.out.println("DB 연결 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
