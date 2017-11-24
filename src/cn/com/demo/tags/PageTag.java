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
		// 1 �����table�����������ṹ

		// 2 �б�table
		// 1)ͷ
		// 2)��¼
		// 3 ���Ƶ�tr
		JspWriter out = this.pageContext.getOut();
		try {                                                                          
        out.println("    // js                                                                                    ");
		out.println("	<script type='text/javascript'>                                                           ");
		out.println("    var pageNo = "+pageContext.getRequest().getAttribute(this.pageNoAttName)+";                                                                  ");
		out.println("    var sumPageCount = "+pageContext.getRequest().getAttribute(this.sumPageCountAttName)+";                                                      ");
		out.println("    function toNext(){                                                                       ");
		out.println("    	// ++��ǰҳ��                                                                         ");
		out.println("    	pageNo++;                                                                             ");
		out.println("    	// ��֤��Χ                                                                           ");
		out.println("    	if(pageNo>sumPageCount){                                                              ");
		out.println("    		pageNo = sumPageCount;                                                            ");
		out.println("    	}                                                                                     ");
		out.println("    	// ��һ���µ�����                                                                     ");
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
		out.println("    	// ��һ���µ�����                                                                     ");
		out.println("    	location.href='"+url+"?"+pageNoParamName+"='+pageNo + '&'+Math.random();           ");
		out.println("    }                                                                                        ");
		out.println("</script>");
			// �ṹ
			out.println("	<table width='535' border='0' align='center' cellpadding='0' cellspacing='0'> ");
			out.println("	  <tr>                                                                        ");
			out.println("	    <td width='500'>                                                          ");

			// �б�table
			out.println(" <table width='98%' border='0' align='center' cellpadding='0' cellspacing='0'>   ");
			out.println("  <tr>                                                                           ");
			// ��̬�����ͷ
			out.println("    <th width='13%'>���</th>                                                    ");
			for (String header : headers) {
				out.println("    <th >"
						+ header
						+ "</th>                                                    ");
			}

			out.println("  </tr>                                                                          ");
			out.println("                                                                                 ");
			// ������
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
			out.println("	    <td align='center'><a href='javascript:toFirst()'>��ҳ</a>                ");
			out.println("	    <a href='javascript:toPrev()'>��һҳ</a>                                  ");
			out.println("	    <a href='javascript:toNext()'>��һҳ</a>                                  ");
			out.println("	    <a href='javascript:toEnd()'>ĩҳ</a></td>                                ");
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
