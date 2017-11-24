<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
    var pageNo = ${pageNo};
    var sumPageCount = ${sumPageCount};
    function toNext(){
    	// ++当前页面
    	pageNo++;
    	// 验证范围
    	if(pageNo>sumPageCount){
    		pageNo = sumPageCount;
    	}
    	// 发一个新的请求
    	location.href='searchMobileListServlet?pageNo='+pageNo + "&"+Math.random();
    }
    
    function toEnd(){
    	location.href='searchMobileListServlet?pageNo='+sumPageCount + "&"+Math.random();
    }
    function toFirst(){
    	location.href='searchMobileListServlet?pageNo=1&'+Math.random();
    }
    function toPrev(){
    	pageNo--;
    	if(pageNo<=0){
    		pageNo = 1;
    	}
    	// 发一个新的请求
    	location.href='searchMobileListServlet?pageNo='+pageNo + "&"+Math.random();
    }
</script>
</head>
<body>
你好
<p>&nbsp;</p>


<table width="535" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="500"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <th width="13%">序号</th>
        <th width="41%">名称</th>
        <th width="27%">价格</th>
        <th width="19%">内存</th>
      </tr>
      
      <c:forEach items="${list }" var="mobile" varStatus="status">
      <c:choose>
         <c:when test="${status.index%2==0 }">
      <tr  bgcolor="#CAFFFF">
         </c:when>
         <c:otherwise>
      <tr  bgcolor="#EAD1FC">
         </c:otherwise>
      </c:choose>
      
        <td>${status.index+1 }</td>
        <td>${mobile.mbName }</td>
        <td>${mobile.mbPrice }</td>
        <td>${mobile.mbMemory }</td>
      </tr>
      </c:forEach>
    </table></td>
  </tr>
  <tr>
    <td align="center"><a href="javascript:toFirst()">首页</a> 
    <a href="javascript:toPrev()">上一页</a> 
    <a href="javascript:toNext()">下一页</a> 
    <a href="javascript:toEnd()">末页</a></td>
  </tr>
</table>



</body>
</html>