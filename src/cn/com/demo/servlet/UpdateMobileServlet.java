package cn.com.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.service.IMobileInfoService;
import cn.com.demo.service.impl.MobileInfoServiceImpl;

public class UpdateMobileServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// ��ȡ����
		// ��װ��Mobile����
		// ����
		// ת���Լ�����ʾ��Ϣ

		req.setCharacterEncoding("UTF-8");
		// ��ȡ����
		String idStr = req.getParameter("mbId");
		String name = req.getParameter("mbName");
		String color = req.getParameter("mbColor");
		String memoryStr = req.getParameter("mbMemory");
		String priceStr = req.getParameter("mbPrice");
		// ��װ��Mobile����
		DmMobileInfo mb = new DmMobileInfo();
		mb.setMbColor(color);
		try {
			mb.setMbId(Integer.parseInt(idStr));
		} catch (Exception e) {
		}
		try {
			mb.setMbMemory(Integer.parseInt(memoryStr));
		} catch (Exception e) {
		}
		mb.setMbName(name);
		try {
			mb.setMbPrice(Double.parseDouble(priceStr));
		} catch (Exception e) {
		}
		// ����
		IMobileInfoService mbService = new MobileInfoServiceImpl();
		String message = "���³ɹ�";
		try{
		   mbService.updateMobile(mb);
		}catch(Exception e){
			message = e.getMessage();
		}
		// ת���Լ�����ʾ��Ϣ
		req.setAttribute("message", message);
		req.getRequestDispatcher("/mobileInfoServlet?mbId="+idStr).forward(req, res);
	}

}
