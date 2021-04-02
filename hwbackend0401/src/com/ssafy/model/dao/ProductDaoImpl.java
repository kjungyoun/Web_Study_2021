package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.dto.ProductDto;
import com.ssafy.util.DBUtil;

public class ProductDaoImpl implements ProductDao{

	@Override
	public void insertProduct(ProductDto productDto)throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int no = productDto.getProductno();
		String name = productDto.getName();
		int price = productDto.getPrice();
		String info = productDto.getInfo();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into product values(?, ?, ?, ?) \n");
			conn = DBUtil.getConnection();
			// 순서!!
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.setString(2, name);
			pstmt.setInt(3, price);
			pstmt.setString(4, info);
			pstmt.executeUpdate();
			
		} 
		finally {
			DBUtil.close(pstmt, conn);
		}

	}

	@Override
	public int getLastNo(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductDto getProduct(int productno) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> listProduct() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductDto> list = new ArrayList<ProductDto>();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from product");
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDto prod = new ProductDto();
				prod.setProductno(rs.getInt("product"));
				prod.setName(rs.getString("name"));
				prod.setPrice(rs.getInt("price"));
				prod.setInfo(rs.getString("info"));
				list.add(prod);
			}
			
		} finally {
			DBUtil.close(rs,pstmt,conn);
		}
		return list;
	}

}
