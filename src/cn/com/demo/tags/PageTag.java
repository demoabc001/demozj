package cn.com.demo.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import ognl.Ognl;

public class PageTag extends BodyTagSupport {
	private String[] headers;
	private String[] attrs;
	private List datas;
	private String url;
	private String pageNoAttName = "pageNo";
	private String sumPageCountAttName = "sumPageCount";
	private String pageNoParamName = "pageNo";

	@Override
	public int doEndTag() throws JspException {
		// js
		// table
		// 1 输出大table，控制整个结构

		// 2 列表table
		// 1)头
		// 2)记录
		// 3 控制的tr
		JspWriter out = this.pageContext.getOut();
		try {                                                                          
        out.println("    // js                                                                                    ");
		out.println("	<script type='text/javascript'>                                                           ");
		out.println("    var pageNo = "+pageContext.getRequest().getAttribute(this.pageNoAttName)+";                                                                  ");
		out.println("    var sumPageCount = "+pageContext.getRequest().getAttribute(this.sumPageCountAttName)+";                                                      ");
		out.println("    function toNext(){                                                                       ");
		out.println("    	// ++当前页面                                                                         ");
		out.println("    	pageNo++;                                                                             ");
		out.println("    	// 验证范围                                                                           ");
		out.println("    	if(pageNo>sumPageCount){                                                              ");
		out.println("    		pageNo = sumPageCount;                                                            ");
		out.println("    	}                                                                                     ");
		out.println("    	// 发一个新的请求                                                                     ");
		out.println("    	location.href='"+url+"?"+pageNoParamName+"='+pageNo + '&'+Math.random();           ");
		out.println("    }                                                                                        ");
		out.println("                                                                                             ");
		out.println("    function toEnd(){                                                                        ");
		out.println("    	location.href='"+url+"?"+pageNoParamName+"='+sumPageCount + '&'+Math.random();     ");
		out.println("    }                                                                                        ");
		out.println("    function toFirst(){                                                                      ");
		out.println("    	location.href='"+url+"?"+pageNoParamName+"=1&'+Math.random();                      ");
		out.println("    }                                                                                        ");
		out.println("    function toPrev(){                                                                       ");
		out.println("    	pageNo--;                                                                             ");
		out.println("    	if(pageNo<=0){                                                                        ");
		out.println("    		pageNo = 1;                                                                       ");
		out.println("    	}                                                                                     ");
		out.println("    	// 发一个新的请求                                                                     ");
		out.println("    	location.href='"+url+"?"+pageNoParamName+"='+pageNo + '&'+Math.random();           ");
		out.println("    }                                                                                        ");
		out.println("</script>");
			// 结构
			out.println("	<table width='535' border='0' align='center' cellpadding='0' cellspacing='0'> ");
			out.println("	  <tr>                                                                        ");
			out.println("	    <td width='500'>                                                          ");

			// 列表table
			out.println(" <table width='98%' border='0' align='center' cellpadding='0' cellspacing='0'>   ");
			out.println("  <tr>                                                                           ");
			// 动态输出表头
			out.println("    <th width='13%'>序号</th>                                                    ");
			for (String header : headers) {
				out.println("    <th >"
						+ header
						+ "</th>                                                    ");
			}

			out.println("  </tr>                                                                          ");
			out.println("                                                                                 ");
			// 表数据
			for(int i=0,length=datas.size();i<length;i++){
				Object obj = datas.get(i);
			out.println("  <tr>                                                                           ");
			out.println(" <td>"+(i+1)+"</td>");
			// OGNL
			Object value = null;
			for(String attr:attrs){
				value = Ognl.getValue(attr, obj);
			out.println("     <td>"+value+"</td>                                                                   ");
			}
			out.println("  </tr>                                                                          ");
			}
			out.println("</table>                                                                         ");

			out.println("	    </td>                                                                     ");
			out.println("	  </tr>                                                                       ");
			out.println("	  <tr>                                                                        ");
			out.println("	    <td align='center'><a href='javascript:toFirst()'>首页</a>                ");
			out.println("	    <a href='javascript:toPrev()'>上一页</a>                                  ");
			out.println("	    <a href='javascript:toNext()'>下一页</a>                                  ");
			out.println("	    <a href='javascript:toEnd()'>末页</a></td>                                ");
			out.println("	  </tr>                                                                       ");
			out.println("	</table>                                                                      ");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.doEndTag();
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public void setAttrs(String[] attrs) {
		this.attrs = attrs;
	}

	public void setDatas(List datas) {
		this.datas = datas;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageNoAttName(String pageNoAttName) {
		this.pageNoAttName = pageNoAttName;
	}

	public void setSumPageCountAttName(String sumPageCountAttName) {
		this.sumPageCountAttName = sumPageCountAttName;
	}

	public void setPageNoParamName(String pageNoParamName) {
		this.pageNoParamName = pageNoParamName;
	}

}
