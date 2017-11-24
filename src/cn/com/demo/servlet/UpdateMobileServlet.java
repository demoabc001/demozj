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
		// 获取数据
		// 封装成Mobile对象
		// 更新
		// 转向自己，提示信息

		req.setCharacterEncoding("UTF-8");
		// 获取数据
		String idStr = req.getParameter("mbId");
		String name = req.getParameter("mbName");
		String color = req.getParameter("mbColor");
		String memoryStr = req.getParameter("mbMemory");
		String priceStr = req.getParameter("mbPrice");
		// 封装成Mobile对象
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
		// 更新
		IMobileInfoService mbService = new MobileInfoServiceImpl();
		String message = "更新成功";
		try{
		   mbService.updateMobile(mb);
		}catch(Exception e){
			message = e.getMessage();
		}
		// 转向自己，提示信息
		req.setAttribute("message", message);
		req.getRequestDispatcher("/mobileInfoServlet?mbId="+idStr).forward(req, res);
	}

}
