package com.ssafy.test.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.test.model.dto.Book;
import com.ssafy.test.util.DBUtil;

public class BookDaoImpl implements BookDao {
	private DBUtil util = DBUtil.getUtil();

	private static BookDao instance = new BookDaoImpl();

	private BookDaoImpl() {
	};

	public static BookDao getInstance() {
		return instance;
	}

	@Override
	public void insert(Book book) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			String sql = "insert into book values (?,?,?,?,?,null)";
			con = util.getConnection();
			stmt = con.prepareStatement(sql);

			// ?에 데이터 셋팅하기
			stmt.setString(1, book.getIsbn());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setInt(4, book.getPrice());
			stmt.setString(5, book.getDesc());

			stmt.executeUpdate();

		} finally {
			util.close(con, stmt);
		}

	}

	@Override
	public Book select(String isbn) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from book where isbn = ?";
			con = util.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, isbn);
			rs = stmt.executeQuery();

			while (rs.next()) {
				return new Book(isbn, rs.getString("title"), rs.getString("author"), rs.getInt("price"),
						rs.getString("desc"));
			}
		} finally {
			util.close(con, stmt, rs);
		}
		return null;
	}

	@Override
	public List<Book> select() throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from book";
			con = util.getConnection();
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			List<Book> books = new ArrayList<Book>();

			while (rs.next()) {
				books.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
						rs.getInt("price"), rs.getString("desc")));
			}
			return books;
		} finally { // 위에서 return을 해도 아래 finally는 무조건 수행!
			util.close(con, stmt, rs);
		}
	}
	
	public static void main(String[] args) {
		try {
			BookDao dao = BookDaoImpl.getInstance();
			
			dao.insert(new Book("1111","처음하는","ssafy",100000,"테스트"));
			System.out.println(dao.select("1111"));
			System.out.println(dao.select());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
