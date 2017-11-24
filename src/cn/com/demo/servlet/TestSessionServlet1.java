package cn.com.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestSessionServlet1 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse arg1)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.println(session.getId());
	}

}
