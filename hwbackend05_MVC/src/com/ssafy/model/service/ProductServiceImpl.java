package com.ssafy.model.service;

import java.sql.Connection;
import java.util.List;

import com.ssafy.model.dao.ProductDao;
import com.ssafy.model.dao.ProductDaoImpl;
import com.ssafy.model.dto.PageBean;
import com.ssafy.model.dto.ProductDto;
import com.ssafy.model.dto.ProductException;
import com.ssafy.util.DBUtil;
import com.ssafy.util.PageUtility;

public class ProductServiceImpl implements ProductService {
	private ProductDao dao = new ProductDaoImpl();
	

	@Override
	public int insertProduct(ProductDto productDto) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			dao.insertProduct(conn, productDto);
			return dao.getLastNo(conn);
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
			throw new ProductException("제품 등록중 오류 발생");
		}finally {
			try {conn.commit();}catch (Exception e) {}
			DBUtil.close(conn);
		}
	}

	@Override
	public ProductDto getProduct(int productno) {
		try {
			return dao.getProduct(productno);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("제품 정보 조회중 오류 발생");
		}
	}

	@Override
	public List<ProductDto> searchAll(PageBean bean) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 페이징 처리를 위해 전체 데이터 개수 조회
			int total = dao.totalCount(conn,bean);
			PageUtility util = new PageUtility(bean.getInterval(),total,bean.getPageNo(),"images/");
			bean.setPageLink(util.getPageBar());
			
			return dao.searchAll(conn, bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("제품 목록 조회중 오류 발생");
		}finally {
			DBUtil.close(conn);
		}
	}

}
