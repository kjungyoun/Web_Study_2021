package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.model.dto.ProductDto;

public interface ProductDao {

	public void insertProduct(ProductDto productDto) throws SQLException;
	public int getLastNo(Connection conn) throws SQLException;
	public ProductDto getProduct(int productno) throws SQLException;
	public List<ProductDto> listProduct() throws SQLException;
}
