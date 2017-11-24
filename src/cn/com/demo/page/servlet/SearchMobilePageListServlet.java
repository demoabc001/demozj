package cn.com.demo.page.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.service.IMobileInfoService;
import cn.com.demo.service.impl.MobileInfoServiceImpl;

public class SearchMobilePageListServlet extends HttpServlet {

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
         // 获取页码
		String pageNoStr = request.getParameter("currPage");
		 // 验证页面的有效性
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(pageNoStr);
		}catch(Exception e){}
		// 下边界
		if(pageNo<=1){
			pageNo = 1;
		}
		// 上边界
		IMobileInfoService service = new MobileInfoServiceImpl();
		int sumPageNum = service.getSumPageCount();
		if(pageNo>=sumPageNum){
			pageNo = sumPageNum;
		}
		// 调用service获取当前页的记录
		List<DmMobileInfo> mobileList = service.searchMobilesByPage(pageNo);
		// 设置到request属性中
		request.setAttribute("mobileList", mobileList);
		// 转向moibleTableList.jsp
		request.getRequestDispatcher("/mobileTableList.jsp").forward(request, response);
	}

}
