package cn.com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.entity.DmUser;
import cn.com.demo.service.IUserService;
import cn.com.demo.service.impl.UserServiceImpl;

public class SearchUserListServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 获取页码
		String pageNoStr = req.getParameter("page");
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(pageNoStr);
		}catch(Exception e){}
		// 掉用service userList 总页码
		IUserService userService = new UserServiceImpl();
		List<DmUser> userList = userService.searchUsersByPage(pageNo);
		int sumPageCount = userService.getSumPageCount();
		// 设置 当前页码 总页码 userList 属性s headers
		req.setAttribute("myPageNo", pageNo);
		req.setAttribute("mySumPage", sumPageCount);
		req.setAttribute("userList", userList);
		
		String[] attrs = {"urUserName","urName","urSex","urAge"};
		String[] headers = {"用户名","姓名","性别","年龄"};
		req.setAttribute("userAttrs", attrs);
		req.setAttribute("userHeaders", headers);
		// 转向userList.jsp
		req.getRequestDispatcher("/userList.jsp").forward(req, res);
	}

}
