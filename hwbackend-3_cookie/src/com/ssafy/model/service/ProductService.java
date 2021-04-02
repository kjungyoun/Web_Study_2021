package com.ssafy.model.service;

import com.ssafy.model.dto.ProductDto;

public interface ProductService {
	public int insertProduct(ProductDto productDto) throws Exception;
	public ProductDto getProduct(int productno) throws Exception;
}
