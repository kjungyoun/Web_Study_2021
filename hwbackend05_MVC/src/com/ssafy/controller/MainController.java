package com.ssafy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.model.dto.MemberDto;
import com.ssafy.model.dto.PageBean;
import com.ssafy.model.dto.ProductDto;
import com.ssafy.model.dto.ProductException;
import com.ssafy.model.service.LoginService;
import com.ssafy.model.service.LoginServiceImpl;
import com.ssafy.model.service.ProductService;
import com.ssafy.model.service.ProductServiceImpl;

@WebServlet( "/main.do" )
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginService loginService;
	ProductService productService;
	
	@Override
	public void init() throws ServletException{
		super.init();
		loginService = new LoginServiceImpl();
		productService = new ProductServiceImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String action = request.getParameter("action");
		System.out.println("action>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+action);
		String url="index.jsp";
		try {
			if(action != null) {
				if(action.equals("login")) {
					url = login(request,response);
				}else if(action.equals("logout")) {
					url = logout(request,response);
				}else if(action.equals("mvInsert")) {
					HttpSession session = request.getSession();
					MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
					
					if(memberDto != null) {			
					url = "product/insert.jsp";
					}else {
						request.setAttribute("msg", "로그인 후에 사용 가능한 페이지입니다.");
					}
				}else if(action.equals("insertProduct")) {
					url = insertProduct(request,response);
				}else if(action.equals("searchLastProduct")) {
					url = searchLastProduct(request,response);
				}else if(action.equals("search")) {
					url = searchProduct(request,response);
				}else if(action.equals("listProduct")) {
					url = listProduct(request,response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			url="error/error.jsp";
		}
		
		if(url.startsWith("redirect:")) { // 경로가 redirect: 로 시작하면 sendRedirect함.
			// redirect:url 이므로 url에서 redirect: 를 제거하여 이동
			response.sendRedirect(url.substring(url.indexOf(":")+1)); 
			
		}else { // 경로가 redirect: 로 시작하지 않으면 기본적으로 forward로 이동.
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
	
	private String listProduct(HttpServletRequest request, HttpServletResponse response) {
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		String pageNo = request.getParameter("pageNo");
		System.out.println("pageNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+pageNo);
		
		PageBean bean = new PageBean(key,word,pageNo);
		request.setAttribute("list", productService.searchAll(bean));
		request.setAttribute("bean", bean);
		
		return "/product/listProduct.jsp";
	}
	private String searchProduct(HttpServletRequest request, HttpServletResponse response) {
		String path = "/product/insertsuccess.jsp";
		try {
			int no = Integer.parseInt(request.getParameter("productno"));
			request.setAttribute("product", productService.getProduct(no));
			String title = request.getParameter("title");
			title = title == null ? "상품 조회" : "등록한 상품 조회";
			request.setAttribute("title", title);
		}catch(ArithmeticException e){
			throw new ProductException("잘못된 상품번호입니다.");
		}
		return path;
	}
	
	private String searchLastProduct(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// 2. 마지막에 등록한 상품 조회
					Cookie[] cookies = request.getCookies();
					String no = null;
					String name = null;
					String price = null;
					String info = null;
					if(cookies != null) {
						for (Cookie cookie : cookies) {
							if(cookie.getName().equals("productNo")) {
								no = cookie.getValue();
							}else if(cookie.getName().equals("productName")) {
								name = URLDecoder.decode(cookie.getValue(), "UTF-8");
							}else if(cookie.getName().equals("price")) {
								price = cookie.getValue();
							}else if(cookie.getName().equals("info")) {
								info = URLDecoder.decode(cookie.getValue(), "UTF-8");
							}
						}
					}
					if(no != null) { // 마지막으로 등록한 상품정보가 쿠키에 있는 경우
						ProductDto product = new ProductDto(Integer.parseInt(no),name,Integer.parseInt(price),info);
						request.setAttribute("product", product);
					}else {
						request.setAttribute("msg", "등록한 상품이 없습니다.");
					}
					return "product/insertsuccess.jsp";
	}
	
	private String insertProduct(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String path = "/index.jsp";
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if(memberDto != null) {			
			ProductDto product = new ProductDto();
			product.setName(request.getParameter("name"));
			product.setPrice(Integer.parseInt(request.getParameter("price")));
			product.setInfo(request.getParameter("info"));
			int no = productService.insertProduct(product);
		
			path = "redirect:main.do?action=search&title=1&productno="+no;
		}else {
			request.setAttribute("msg", "로그인 후에 사용 가능한 페이지입니다.");
		}
		
		return path;
	}
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index.jsp";
	}
	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		
		String url="index.jsp";
		try {
			MemberDto userinfo = loginService.login(userid, userpwd); // 오류가 나지 않으면 인증된 경우
			
			// 인증 처리
			
			// 1. session에 사용자 정보 추가하기
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", userinfo);
			
			
			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
		}
		return url;
	}
}
