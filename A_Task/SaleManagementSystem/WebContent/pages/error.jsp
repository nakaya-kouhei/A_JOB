<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%
	HttpSession hs = request.getSession();
	String error = (String)request.getAttribute("error");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー画面</title>
</head>
<body>
	<br>
	<bean:message key="error.message" /><br><br>
	<% switch(error) {
	 	case "1" : out.print("ログインできません。氏名とパスワードを確認してください。"); break;
	 	case "2" : out.print("不正なデータがあります。管理者に連絡してください。"); break;
	    default: out.print("エラーが発生しました。管理者に連絡してください。"); break; } %><br><br>
	<html:link href="pages/login.jsp"><bean:message key="error.tologin" /></html:link>
</body>
</html>