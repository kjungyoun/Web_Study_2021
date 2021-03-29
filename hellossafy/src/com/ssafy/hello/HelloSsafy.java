package com.ssafy.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloSsafy
 */
@WebServlet("/hello")
public class HelloSsafy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html> ");
		out.println("	<body>");
		out.println("		Hello SSAFY!!<br>");
		out.println("		안녕 싸피!!");
		out.println("	</body>");
		out.println("</html>");

	}

}
