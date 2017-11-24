<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/mytags" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 fdsafsd<br/>
    <my:page datas="${userList }" 
            url="searchUserListServlet" 
            attrs="${userAttrs }" 
            headers="${userHeaders }"
            pageNoAttName="myPageNo" 
            pageNoParamName="page" 
            sumPageCountAttName="mySumPage"/>
 <br/>a,<br>
</body>
</html>