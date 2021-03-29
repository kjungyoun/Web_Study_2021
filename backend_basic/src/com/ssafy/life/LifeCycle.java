package com.ssafy.life;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lifecycle.do")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Multi Thread에 의해 공유되는 공유 변수 => 수정되는 데이터를 사용하거나, 클라이언트의 정보를 속성으로 선언하면 안됨.
	int count =1;		
       
	public LifeCycle() {
		System.out.println("Constructor() Call!!!!!");
	}

	@Override
	// 서비스 중에 한번 수행되는 call back 함수, servlet의 초기화를 담당
	public void init() throws ServletException {
		System.out.println("init() Call!!!!!");
	}
	
	// Clien가 요청할 때마다 호출되는 함수 => 클라이언트의 요청을 서비스하는 함수
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() Call!!!!"+(count++));
	}

	@Override
	// 서비스 중에 한번 수행되는 call back 함수, 객체가 서버에서 unload될 때 한번 호출됨
	public void destroy() {
		System.out.println("destroy Call!!!!");
	}
}
