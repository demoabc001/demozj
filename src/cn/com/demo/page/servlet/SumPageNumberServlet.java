package cn.com.demo.page.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.service.IMobileInfoService;
import cn.com.demo.service.impl.MobileInfoServiceImpl;

public class SumPageNumberServlet extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		IMobileInfoService service = new MobileInfoServiceImpl();
		int sumPageNum = service.getSumPageCount();
		out.print(sumPageNum);// ²»Òªln  30\n
		
		out.flush();
		out.close();
	}

}
