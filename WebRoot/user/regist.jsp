<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'Regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<h1>Regist</h1>
	<p style="color:red;font-weight:900">${msg }</p>
	<form action="<c:url value='/RegistServlet'/>" method="post">
		用户名:<input type="text" name="username" value="${user.username }"/>${errors.username }<br>
		密　码:<input type="password" name="password" value="${user.password }"/>${errors.password }<br/>
		验证码:<input type="text" name="verifyCode" value="${user.verifyCode }" size="3"/>
		<img id="img" src="<c:url value='/VerifyCodeServlet'/>" border="2"/>
		<a href="javascript:change()">看不清?换一张</a>${errors.verifyCode }<br>
 		<input type="submit" value="注册"/>
	</form>
  </body>
  <script type="text/javascript">
	function change() {
		/*
			1.获取标签
			2.设置标签的src属性值
		*/
		//获取img标签
		var img = document.getElementById("img");
		//设置标签的src属性值
		img.src =" <c:url value='/VerifyCodeServlet?time='/>"+new Date().getTime();
	}
  </script>
</html>


