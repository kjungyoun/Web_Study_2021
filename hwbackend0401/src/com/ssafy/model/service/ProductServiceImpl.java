package com.ssafy.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.model.dao.ProductDao;
import com.ssafy.model.dao.ProductDaoImpl;
import com.ssafy.model.dto.ProductDto;

public class ProductServiceImpl implements ProductService {
	
	

	@Override
	public void insertProduct(ProductDto productDto) throws SQLException {
		ProductDao dao = new ProductDaoImpl();
		dao.insertProduct(productDto);
	}

	@Override
	public int getLastNo(Connection conn) throws SQLException {
		return 0;
	}

	@Override
	public ProductDto getProduct(int productno) throws SQLException {
		return null;
	}

	@Override
	public List<ProductDto> listProduct() throws SQLException {
		ProductDao dao = new ProductDaoImpl();
		return dao.listProduct();
	}

}
