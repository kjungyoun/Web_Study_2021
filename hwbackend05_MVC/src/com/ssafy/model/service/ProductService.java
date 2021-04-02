package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.PageBean;
import com.ssafy.model.dto.ProductDto;

public interface ProductService {
	public int insertProduct(ProductDto productDto);
	public ProductDto getProduct(int productno);
	public List<ProductDto> searchAll(PageBean bean);
}
