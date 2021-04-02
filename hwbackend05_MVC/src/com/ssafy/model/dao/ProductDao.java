package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.model.dto.PageBean;
import com.ssafy.model.dto.ProductDto;

public interface ProductDao {

	public void insertProduct(Connection conn,ProductDto productDto) throws SQLException;
	public int getLastNo(Connection conn) throws SQLException;
	public ProductDto getProduct(int productno) throws SQLException;
	public int totalCount (Connection conn,PageBean bean)throws SQLException;
	public List<ProductDto> searchAll(Connection conn, PageBean bean)throws SQLException;
}
