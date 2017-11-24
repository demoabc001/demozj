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
		// ���յ�ǰҳ��
		String pageStr = req.getParameter("pageNo");
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(pageStr);
		}catch(Exception e){}
		// ��ȡ���ݣ����ϡ���ҳ�룩
		IMobileInfoService service = new MobileInfoServiceImpl();
		int sumPageCount = service.getSumPageCount();
		if(pageNo>sumPageCount){
			pageNo = sumPageCount;
		}
		// ��ǰҳ�ļ���
		List<DmMobileInfo> list = service.searchMobilesByPage(pageNo);
		// ��������
		req.setAttribute("list", list);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("sumPageCount", sumPageCount);
		
		// attrs
		String[] attrs = {"mbName","mbPrice","mbMemory"};
		req.setAttribute("attrs", attrs);
		// headers
		String[] headers = {"����","�۸�","�ڴ�"};
		req.setAttribute("headers", headers);
		
		// ת��
		req.getRequestDispatcher("/my.jsp").forward(req, res);
	}
}
