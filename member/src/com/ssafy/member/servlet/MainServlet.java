package com.ssafy.member.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.member.dto.MemberDto;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		@Override
		public void init() throws ServletException {
			super.init();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String act = request.getParameter("act");
		if("mvregister".equals(act)) {
			response.sendRedirect(root+"/user/join.jsp");
		}else if("mvlogin".equals(act)) {
			response.sendRedirect(root+"/user/login.jsp");
		}else if("login".equals(act)) {
			login(request,response);
		}else if("logout".equals(act)) {
			logout(request,response);
		}else if("".equals(act)) {
			
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userinfo");
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("userid");
		String pass = request.getParameter("userpwd");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto memberDto = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8", "ssafy", "ssafy");
			String sql = "select username, email \n";
			sql += "from ssafy_member \n";
			sql += "where userid = ? and userpwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserName(rs.getString("username"));
				memberDto.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String path = "/index.jsp";
		if(memberDto != null) { // 성공
			// session 설정
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", memberDto);
			
			// Cookie 설정
			String idsv = request.getParameter("idsave");
			if("saveok".equals(idsv)) { // 아이디 저장을 한다면
				
				// 어떤 이름으로 어떤 데이터를 저장할 지?
				Cookie cookie = new Cookie("save_id", id);
				
				// 어디에서 사용할 쿠키인지? (url을 인자로 넘김)
				cookie.setPath(request.getContextPath());
				
				// 언제까지 사용할 쿠키인지? (안에 시간을 넣음)
				cookie.setMaxAge(60 * 60 *24 * 365 * 40);
				
				// 서버에서 클라이언트로 cookie를 보냄
				response.addCookie(cookie);
			}else { // 아이디 저장 X
				Cookie cookies[] = request.getCookies();
				String idck ="";
				String saveid ="";
				if(cookies != null){
					for(Cookie cookie : cookies){
						if(cookie.getName().equals("save_id")){
							// 쿠키를 지우는 메서드가 없으므로 만료시간을 0초로 하여 지움
							cookie.setMaxAge(0);
							response.addCookie(cookie);
							break;
						}
					}
				}
			}
			
//			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		}else { // 실패
			request.setAttribute("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
//			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			path = "/user/login.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post와 get을 모두 한 곳에 모아 처리
		// post는 한글이 깨지므로 Encodig 처리를 한뒤 보냄
		request.setCharacterEncoding("utf-8");
		doGet(request,response);
	}

}
