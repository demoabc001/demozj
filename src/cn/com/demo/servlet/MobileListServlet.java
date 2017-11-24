package cn.com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.service.IMobileInfoService;
import cn.com.demo.service.impl.MobileInfoServiceImpl;
import cn.com.demo.vo.MobileInfoVO;

public class MobileListServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 查询所有的手机
		// 创建Service
		IMobileInfoService mobileService = new MobileInfoServiceImpl();
		// 查询
		List<DmMobileInfo> mobileList = mobileService.searchAllMobiles();
		// 获取所有的cookie
		Cookie[] cookies = req.getCookies();
		Map<String, String> dotMap = null;
		if (cookies != null) {
			dotMap = new HashMap<String, String>();
			// 将cookie的name和value put到map
			for (Cookie cookie : cookies) {
				dotMap.put(cookie.getName(), cookie.getValue());
			}
		}
		// 调用service的sort排序
		List<MobileInfoVO> voList = mobileService.sort(mobileList, dotMap);
		// 显示手机列表
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 循环显示
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>无标题文档</title>");
		out.println("</head>");

		out.println("<body>");
		out.println("<p>&nbsp;</p>");
		out.println("<table width='713' border='0' align='center' cellpadding='0' cellspacing='0'>");
		out.println("  <tr bgcolor='#B7B7B7'>");
		out.println("    <th width='86'>序号</th>");
		out.println("    <th width='234'>手机名</th>");
		out.println("    <th width='135'>价格</th>");
		out.println("    <th width='129'>颜色</th>");
		out.println("    <th width='129'>内存</th>");
		out.println("  </tr>");

		if (voList != null) {
			DmMobileInfo mb = null;
			for (int i = 0; i < voList.size(); i++) {
				mb = voList.get(i).getMobile();
				if (i % 2 == 0) {
					out.println("  <tr bgcolor='#E1ECEB'>");
				} else {
					out.println("  <tr bgcolor='#CFF0F8'>");
				}
				out.println("    <td>" + (i + 1) + "</td>");
				out.println("    <td><a href='mobileInfoServlet?mbId="
						+ mb.getMbId() + "'>" + mb.getMbName() + "</a></td>");
				out.println("    <td>" + mb.getMbPrice() + "</td>");
				out.println("    <td>" + mb.getMbColor() + "</td>");
				out.println("    <td>" + mb.getMbMemory() + "</td>");
				out.println("  </tr>");
			}
		}

		out.println("</table>");
		out.println("</body>");
		out.println("</html>");

		out.flush();
		out.close();
	}

}
