package com.ssafy.test.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.test.model.dao.BookDao;
import com.ssafy.test.model.dao.BookDaoImpl;
import com.ssafy.test.model.dto.Book;

public class BookServiceImpl implements BookService {
	private BookDao dao = BookDaoImpl.getInstance();
	private static BookService instance = new BookServiceImpl();
	private BookServiceImpl() {};
	public static BookService getInstance() {
		return instance;
	}
	
	
	@Override
	public void insert(Book product) throws SQLException {
		dao.insert(product);

	}

	@Override
	public Book select(String pCode) throws SQLException {
		return dao.select(pCode);
	}

	@Override
	public List<Book> select() throws SQLException {
		return dao.select();
	}

}
