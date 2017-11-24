package cn.com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestSessionServlet2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.println(session.getId());
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String url1 = "testSessionServlet1";
		url1 = res.encodeURL(url1);
		out.println("<a href='"+url1+"'>Session1</a><br/>");
		String url2 = "testSessionServlet2";
		url2 = res.encodeURL(url2);
		out.println("<a href='"+url2+"'>Session2</a><br/>");
		
	}
}
