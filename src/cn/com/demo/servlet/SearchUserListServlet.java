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
		// ��ȡҳ��
		String pageNoStr = req.getParameter("page");
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(pageNoStr);
		}catch(Exception e){}
		// ����service userList ��ҳ��
		IUserService userService = new UserServiceImpl();
		List<DmUser> userList = userService.searchUsersByPage(pageNo);
		int sumPageCount = userService.getSumPageCount();
		// ���� ��ǰҳ�� ��ҳ�� userList ����s headers
		req.setAttribute("myPageNo", pageNo);
		req.setAttribute("mySumPage", sumPageCount);
		req.setAttribute("userList", userList);
		
		String[] attrs = {"urUserName","urName","urSex","urAge"};
		String[] headers = {"�û���","����","�Ա�","����"};
		req.setAttribute("userAttrs", attrs);
		req.setAttribute("userHeaders", headers);
		// ת��userList.jsp
		req.getRequestDispatcher("/userList.jsp").forward(req, res);
	}

}
