package com.ssafy.backend.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.backend.dto.Book;


@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public MainServlet() {
        super();
            }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		if(action != null) {
			if(action.equals("regist")) {
				doRegist(request,response);
			}
		}
	}


	private void doRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 클라이언트의 요청 데이터 읽기
		String isbn= request.getParameter("isbn");
		String title = request.getParameter("title");
		String author= request.getParameter("author");
		String sprice = request.getParameter("price");
		String desc = request.getParameter("desc");
		int price = 0;
		try { price = Integer.parseInt(sprice);}catch(Exception e) {}
		Book book = new Book(isbn,title,author,price,desc);
		
		// request바구니에 book 객체를 담음
		request.setAttribute("book", book);
		
		request.getRequestDispatcher("/member/result.jsp").forward(request, response);
		
	}



}
