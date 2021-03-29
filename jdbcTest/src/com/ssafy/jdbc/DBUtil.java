package com.ssafy.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
   static String url = "jdbc:mysql://localhost:3306/ssafydb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
   static String id = "ssafy";
   static String pw = "ssafy";
   static {
      try{
         Class.forName("com.mysql.cj.jdbc.Driver");
      }catch(Exception e){
         System.out.println("driver를 찾을 수 없습니다.");
         }
   }
   public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(url, id, pw);
   }
   public static void close(Connection con) {
      try {
         if(con!=null) con.close();
      } catch (Exception e) {
      }
   }
   public static void close(PreparedStatement stmt) {
      try {
         if(stmt!=null) stmt.close();
      } catch (Exception e) {
      }
   }
   public static void close(ResultSet rs) {
      try {
         if(rs!=null) rs.close();
      } catch (Exception e) {
      }
   }
}
