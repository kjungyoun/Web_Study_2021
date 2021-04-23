package com.ssafy.test.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ssafy.test.model.dto.Book;

public interface BookDao {
	void insert(Book product) throws SQLException;

	Book select(String pCode) throws SQLException;

	List<Book> select() throws SQLException;
}
