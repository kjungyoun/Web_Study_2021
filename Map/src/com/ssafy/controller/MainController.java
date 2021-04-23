package com.ssafy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ssafy.model.dto.HouseInfo;
import com.ssafy.model.service.HouseServiceImpl;

@WebServlet( "/main.do" )
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HouseServiceImpl hService = new HouseServiceImpl();
	@Override
	public void init() throws ServletException{
		super.init();
		
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
		System.out.println("IP>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+request.getRemoteAddr());
		System.out.println("action>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+action);
		String url="index.jsp";
		try {
			if("baseAddressGuSearch".equals(action)) {
				url = baseAddressGuSearch(request,response);
			}else if("baseAddressDongSearch".equals(action)) {
				url = baseAddressDongSearch(request,response);
			}else if("searchHouse".equals(action)) {
				url = searchHouse(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			url="error/error.jsp";
		}
		
		if(url.startsWith("{") || url.startsWith("[") ) {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().append(url);
		}else if(url.startsWith("redirect:")) {
			response.sendRedirect(url.substring(url.indexOf(":")+1));
		}else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	private String searchHouse(HttpServletRequest request, HttpServletResponse response) {
		String dong = request.getParameter("dong");
		List<HouseInfo> list = hService.searchHouseInfo(dong);
		request.setAttribute("houses", list);
		request.setAttribute("mainAttent", list.get(0));
		return "index.jsp";
	}

	private String baseAddressDongSearch(HttpServletRequest request, HttpServletResponse response) {
		String gu = request.getParameter("local");
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(hService.searchDong(gu));
		System.out.println("gu>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+gu);
		System.out.println(json);
		return json;
	}

	private String baseAddressGuSearch(HttpServletRequest request, HttpServletResponse response) {
		String city = request.getParameter("local");
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(hService.searchGu(city));
			System.out.println("city>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+city);
			System.out.println(json);
			return json;
	}

	
}







