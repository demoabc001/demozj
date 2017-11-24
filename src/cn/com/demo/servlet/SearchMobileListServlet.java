package cn.com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.service.IMobileInfoService;
import cn.com.demo.service.impl.MobileInfoServiceImpl;

public class SearchMobileListServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 接收当前页码
		String pageStr = req.getParameter("pageNo");
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(pageStr);
		}catch(Exception e){}
		// 获取数据（集合、总页码）
		IMobileInfoService service = new MobileInfoServiceImpl();
		int sumPageCount = service.getSumPageCount();
		if(pageNo>sumPageCount){
			pageNo = sumPageCount;
		}
		// 当前页的集合
		List<DmMobileInfo> list = service.searchMobilesByPage(pageNo);
		// 设置数据
		req.setAttribute("list", list);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("sumPageCount", sumPageCount);
		
		// attrs
		String[] attrs = {"mbName","mbPrice","mbMemory"};
		req.setAttribute("attrs", attrs);
		// headers
		String[] headers = {"名称","价格","内存"};
		req.setAttribute("headers", headers);
		
		// 转向
		req.getRequestDispatcher("/my.jsp").forward(req, res);
	}
}
