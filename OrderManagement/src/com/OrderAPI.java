package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderAPI
 */
@WebServlet("/OrderAPI")
public class OrderAPI extends HttpServlet {
	
	
	Order orderObj =new Order();
       
    
    public OrderAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = orderObj.insertOrder(request.getParameter("name"), 
				 request.getParameter("category"), 
				request.getParameter("paymethod"), 
				request.getParameter("payment")); 
				response.getWriter().write(output); 
				
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = orderObj.updateOrder(paras.get("hidIdSave").toString(), 
		 paras.get("name").toString(), 
		 paras.get("category").toString(), 
		paras.get("paymethod").toString(), 
		paras.get("payment").toString()); 
		response.getWriter().write(output); 
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = orderObj.deleteOrder(paras.get("id").toString()); 
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	
	

}
