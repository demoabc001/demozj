package cn.com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.service.IMobileInfoService;
import cn.com.demo.service.impl.MobileInfoServiceImpl;

public class MobileInfoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 获取id

		// 根据id获取手机对象

		// id对应手机的点击次数++
		// 添加到响应中

		// 显示手机信息

		// 获取id
		String idStr = req.getParameter("mbId");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
		}
		// 根据id获取手机对象
		IMobileInfoService mbService = new MobileInfoServiceImpl();
		DmMobileInfo mb = mbService.searchMobile(id);

		// id对应手机的点击次数++
		// 获取但求id的旧点击次数
		int dot = 0;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(idStr)) {
					try {
						dot = Integer.parseInt(cookie.getValue());
					} catch (Exception e) {
					}
					break;
				}
			}
		}
		dot++;
		// 添加到响应中
		Cookie dotCookie = new Cookie(idStr,String.valueOf(dot));
		res.addCookie(dotCookie);

		// 显示手机信息
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 循环显示
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>无标题文档</title>");

		out.println("<script type='text/javascript'>");
		out.println("function toUpdate(){");
		out.println("	document.form1.action='updateMobileServlet';");
		out.println("	document.form1.submit();");
		out.println("}");
		out.println("function toDelete(){");
		out.println("	if(confirm('您确定要删除该手机吗?')){");
		out.println("	    document.form1.action = 'deleteMobileServlet';");
		out.println("	    document.form1.submit();");
		out.println("	}");
		out.println("}");

		out.println("function toList(){");
		out.println("	location.href='mobileListServlet?'+Math.random();");
		out.println("}");
		out.println("</script>");

		out.println("</head>");

		out.println("<body>");
		out.println("<p>&nbsp;</p>");

		out.println("<form name='form1' method='post' action=''>");
		out.println("  <input type='hidden' name='mbId' value='" + mb.getMbId()
				+ "'/>");
		out.println("  <table width='443' border='0' align='center' cellpadding='0' cellspacing='0'>");
		out.println("    <tbody>");
		out.println("      <tr>");
		out.println("        <td width='183' align='right'>&nbsp;手机名称</td>");
		out.println("        <td width='260'><input name='mbName' type='text' id='mbName' value='"
				+ mb.getMbName() + "'></td>");
		out.println("      </tr>");
		out.println("      <tr>");
		out.println("        <td align='right'>手机颜色</td>");
		out.println("        <td><input name='mbColor' type='text' id='mbColor' value='"
				+ mb.getMbColor() + "'></td>");
		out.println("      </tr>");
		out.println("      <tr>");
		out.println("        <td align='right'>手机内存</td>");
		out.println("        <td><input name='mbMemory' type='text' id='mbMemory' value='"
				+ mb.getMbMemory() + "'></td>");
		out.println("      </tr>");
		out.println("      <tr>");
		out.println("        <td align='right'>手机价格</td>");
		out.println("        <td><input name='mbPrice' type='text' id='mbPrice' value='"
				+ mb.getMbPrice() + "'></td>");
		out.println("      </tr>");
		out.println("      <tr>");
		out.println("        <td colspan='2' align='center'><input type='button' name='button' id='button' value='修改' onClick='toUpdate()'>");
		out.println("        <input type='button' name='button2' id='button2' value='删除' onClick='toDelete()'>");
		out.println("        <input type='button' name='button3' id='button3' value='返回查询' onClick='toList()'></td>");
		out.println("      </tr>");
		out.println("    <tbody>");
		out.println("  </table>");
		out.println("</form>");

		String message = (String) req.getAttribute("message");
		if (message != null && !"".equals(message)) {
			out.println("<script>");
			out.println("   alert('" + message + "');");
			out.println("</script>");
		}

		out.println("</body>");
		out.println("</html>");
	}

}
