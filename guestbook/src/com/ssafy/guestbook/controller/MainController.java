package com.ssafy.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.service.GuestBookServiceImpl;
import com.ssafy.guestbook.model.service.MemberServiceImpl;



@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String root = request.getContextPath();
		
		if("mvregister".equals(act)) {
			response.sendRedirect(root+"/user/join.jsp");
		}else if("login".equals(act)) {
			login(request,response);
		}else if("logout".equals(act)) {
			logout(request,response);
		}else if("mvwrite".equals(act)) {
			response.sendRedirect(root+"/guestbook/write.jsp");
		}else if("write".equals(act)) {
			registerArticle(request,response);
		}else if("list".equals(act)) {
			listArticle(request,response);
		}else if("".equals(act)) {
			
		}else if("".equals(act)) {
			
		}else if("".equals(act)) {
			
		}else if("".equals(act)) {
			
		}
	}

	private void listArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		try {
			List<GuestBookDto> list = GuestBookServiceImpl.getGuestBookService().listArticle(key, word);
			request.setAttribute("articles", list);
			path = "/guestbook/list.jsp";
		} catch (Exception e) {
			path="error/error500.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	private void registerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if(memberDto !=null) {
			GuestBookDto guestBookDto = new GuestBookDto();
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			guestBookDto.setUserId(memberDto.getUserId());
			guestBookDto.setSubject(subject);
			guestBookDto.setContent(content);
			
			try {
				GuestBookServiceImpl.getGuestBookService().registerArticle(guestBookDto);
				response.sendRedirect("main?act=list");
				return;
				
			} catch (Exception e) {
				// 글 작성중에 세션이 만료될 경우
				request.setAttribute("msg", "글작성 중 문제가 발생했습니다.");
				e.printStackTrace();
			}	
			
		}else {
			request.setAttribute("msg", "로그인 후 사용가능한 서비스 입니다.");
//			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			path = "/error/error500.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect(request.getContextPath());
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		
		MemberDto memberDto = MemberServiceImpl.getMemberService().login(userId, userPwd);
		
		
		String path = "/index.jsp";
		if(memberDto != null) { // 성공
			// session 설정
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", memberDto);
			
			// Cookie 설정
			String idsv = request.getParameter("idsave");
			if("saveok".equals(idsv)) { // 아이디 저장을 한다면
				
				// 어떤 이름으로 어떤 데이터를 저장할 지?
				Cookie cookie = new Cookie("save_id", userId);
				
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
			path = "/error/error500.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}


}
