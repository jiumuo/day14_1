<%@ page language="java" import="java.util.*,top.jiumu.user.domain.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%
  	User user = (User) request.getSession().getAttribute("user");
  	if(user==null||user.getUsername()==null||user.getUsername().trim().isEmpty()||user.getPassword()==null||user.getPassword().trim().isEmpty()){
  		request.setAttribute("msg","请登录后进入主页!");
  		request.getRequestDispatcher("/user/login.jsp").forward(request, response);
  	}
  %>
  <body>
	欢迎欢迎,热烈欢迎<a style="color:red;font-size:36">${user.username }</a>领导登录!!!
  </body>
</html>
