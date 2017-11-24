<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
   var sumPageNum = 1;
   var currPage = 1;
   
   function first(){
	   currPage = 1;
	   sendAjax();
   }
   
   function next(){
	   currPage++;
	   if(currPage>=sumPageNum){
		   currPage = sumPageNum;
	   }
	   sendAjax();
   }
   
   function sendAjax(){
	   $.post('searchMobilePageListServlet',{currPage:currPage},function(data){
		   $("#tableDiv").html(data);
	   });
   }
   function getSumPageNum(){
	   $.post('sumPageNumberServlet',function(data){
		   sumPageNum = parseInt(data);
	   });
   }
   
   $(document).ready(function(){
	   getSumPageNum();
	   first();
   });
</script>
</head>
<body>
   
   <table width="500">
      <tr>
        <td>
           <div id="tableDiv">
           </div>
        </td>
      </tr>
      <tr>
        <td>
           <a href="javascript:first()">首页</a>
           <a href="javascript:prev()">上一页</a>
           <a href="javascript:next()">下一页</a>
           <a href="javascript:last()">末页</a>
        </td>
      </tr>
      
   </table>

</body>
</html>