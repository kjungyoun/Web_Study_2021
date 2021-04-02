package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.ssafy.model.dto.ProductDto;

public interface ProductDao {

	public void insertProduct(Connection conn,ProductDto productDto) throws SQLException;
	public int getLastNo(Connection conn) throws SQLException;
	public ProductDto getProduct(int productno) throws SQLException;
}
