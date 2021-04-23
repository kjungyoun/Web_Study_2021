package com.ssafy.test.servlet;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.test.model.dto.Book;
import com.ssafy.test.model.dto.User;
import com.ssafy.test.model.service.BookService;
import com.ssafy.test.model.service.BookServiceImpl;
import com.ssafy.test.model.service.UserService;
import com.ssafy.test.model.service.UserServiceImpl;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final UserService userService = UserServiceImpl.getInstance();
    private final BookService bookService = BookServiceImpl.getInstance();
	
	
    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request,response);
	}

	
	
	private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String action = request.getParameter("act");
		if("login".equals(action)) {
			login(request,response);
		}else if("list".equals(action)) {
			list(request,response);
		}else if("registForm".equals(action)) {
			registForm(request,response);
		}else if("regist".equals(action)) {
			regist(request,response);
		}else if("search".equals(action)) {
			search(request,response);
		}
		
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String isbn = request.getParameter("isbn");
			Book book = bookService.select(isbn);
			request.setAttribute("book", book);
			request.getRequestDispatcher("/regist_result.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
		}
		
	}

	private void regist(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		try {
			int price = Integer.parseInt(request.getParameter("price"));
			String desc = request.getParameter("desc");
			
			Book book = new Book(isbn,title,author,price,desc);
			bookService.insert(book);
			
			response.sendRedirect(request.getContextPath()+"/main?act=search&isbn="+isbn);
			
		}catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("msg", "도서 등록 실패: " + isbn +"번의 책은 이미 등록된 책입니다.");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
		}
		
	}

	private void registForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
		}
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Book> books = bookService.select();
			request.setAttribute("books", books);
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
		}
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		try {
			User user= userService.select(id, pass);
			if(user != null && user.getPass().equals(pass)) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
			else {
				request.setAttribute("msg", "로그인 실패!");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
		}
		
	}

}
