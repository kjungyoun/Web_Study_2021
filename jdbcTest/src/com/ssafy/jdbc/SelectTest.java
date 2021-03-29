package com.ssafy.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTest {

   public static void main(String[] args) {
      Connection con = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      try {
         con = DBUtil.getConnection();
         String sql = "select * from employees";
         stmt = con.prepareStatement(sql);
         rs = stmt.executeQuery();
         
         while(rs.next()) {
            System.out.printf("%s\t%s\t%s\t%s\t\n",
                  rs.getString("employee_id"),
                  rs.getString("job_id"),
                  rs.getString("last_name"),
                  rs.getString("salary"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if(rs!=null)   try{rs.close();}catch(Exception e1){}
         if(stmt!=null)   try{stmt.close();}catch(Exception e1){}
         if(con!=null)   try{con.close();}catch(Exception e1){}
      }
      
   }

}