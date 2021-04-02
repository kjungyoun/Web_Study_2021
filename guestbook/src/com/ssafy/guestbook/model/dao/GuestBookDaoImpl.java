package com.ssafy.guestbook.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.util.DBUtil;

public class GuestBookDaoImpl implements GuestBookDao {

	private static GuestBookDao guestBookDao;

	private GuestBookDaoImpl() {
	}

	public static GuestBookDao getGuestBookDao() {
		if (guestBookDao == null)
			guestBookDao = new GuestBookDaoImpl();
		return guestBookDao;
	}

	@Override
	public void registerArticle(GuestBookDto guestBookDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnect();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("insert into guestbook (userid,subject, content, regtime) \n");
			insertMember.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, guestBookDto.getUserId());
			pstmt.setString(2, guestBookDto.getSubject());
			pstmt.setString(3, guestBookDto.getContent());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}

	}

	@Override
	public List<GuestBookDto> listArticle(String key, String word) throws SQLException {
		List<GuestBookDto> list = new ArrayList<GuestBookDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnect();
			StringBuilder sql = new StringBuilder();
			sql.append("select articleno, userid, subject, content,regtime \n");
			sql.append("from guestbook \n");
			if (!word.isEmpty()) {
				if ("subject".equals(key)) {
					sql.append("where subject like ? \n");
				} else {
					sql.append("where " + key + "= ? \n");
				}
			}
			sql.append("order by articleno desc \n");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
			if (!word.isEmpty()) {
				if ("subject".equals(key)) {
					pstmt.setString(1, "%"+word+"%");
				}else {
					pstmt.setString(1, word);
				}
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GuestBookDto dto = new GuestBookDto();
				dto.setArticleNo(rs.getInt("articleno"));
				dto.setUserId(rs.getString("userid"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setRegtime(rs.getString("regtime"));
				list.add(dto);
			}
			
		} finally {
			DBUtil.close(rs,pstmt,conn);
		}
		return list;
	}

	@Override
	public GuestBookDto getArticle(int articleNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyAricle(GuestBookDto guestBookDto) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		// sql Test 코드
		
		try {
			List<GuestBookDto> list = GuestBookDaoImpl.getGuestBookDao().listArticle("subject", "안녕");
			for (GuestBookDto dto : list) {
				System.out.println(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
