<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <table width="98%">
      <tr>
         <th>序号</th>
         <th>名称</th>
         <th>价格</th>
         <th>内存</th>
      </tr>
      
      <c:forEach items="${mobileList }" var="mobile" varStatus="status">
      <tr>
         <td>${status.index+1 }</td>
         <td>${mobile.mbName }</td>
         <td>${mobile.mbPrice }</td>
         <td>${mobile.mbMemory }</td>
      </tr>
      </c:forEach>
   </table>
</body>
</html>