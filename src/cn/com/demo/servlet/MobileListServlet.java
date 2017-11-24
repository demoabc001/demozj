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
		// ��ѯ���е��ֻ�
		// ����Service
		IMobileInfoService mobileService = new MobileInfoServiceImpl();
		// ��ѯ
		List<DmMobileInfo> mobileList = mobileService.searchAllMobiles();
		// ��ȡ���е�cookie
		Cookie[] cookies = req.getCookies();
		Map<String, String> dotMap = null;
		if (cookies != null) {
			dotMap = new HashMap<String, String>();
			// ��cookie��name��value put��map
			for (Cookie cookie : cookies) {
				dotMap.put(cookie.getName(), cookie.getValue());
			}
		}
		// ����service��sort����
		List<MobileInfoVO> voList = mobileService.sort(mobileList, dotMap);
		// ��ʾ�ֻ��б�
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		// ѭ����ʾ
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>�ޱ����ĵ�</title>");
		out.println("</head>");

		out.println("<body>");
		out.println("<p>&nbsp;</p>");
		out.println("<table width='713' border='0' align='center' cellpadding='0' cellspacing='0'>");
		out.println("  <tr bgcolor='#B7B7B7'>");
		out.println("    <th width='86'>���</th>");
		out.println("    <th width='234'>�ֻ���</th>");
		out.println("    <th width='135'>�۸�</th>");
		out.println("    <th width='129'>��ɫ</th>");
		out.println("    <th width='129'>�ڴ�</th>");
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
