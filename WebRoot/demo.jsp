<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!
  int times = 0;
  void outHello(JspWriter out,int myTimes){
     //out.println("当前页面已经点击了" + times +"次");
     try{
        out.println("当前页面已经点击了" + (++times) +"次" + myTimes);
     }catch(Exception e){}
  }
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <!-- body的内容 -->
   
   <%--输出动态的hello world --%>
   <%
      /*
        java块注释
      */
      // 获取当前日期
      Date date = new Date();
      // 格式化
      int myTimes = 0;
      myTimes++;
      
      outHello(out,myTimes);
   %>
</body>
</html>