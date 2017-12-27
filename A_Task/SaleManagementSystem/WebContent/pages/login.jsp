<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h3><bean:message key="login.welcome" /></h3><br>
	<html:errors /><br>
	<html:form action="do/LogInAction" method="post" focus="userName">
			<bean:message key="login.name" /><br>
		<html:text property="userName" size="10" maxlength="15" value="天野　洋平" /><br><br>
			<bean:message key="login.loginid" /><br>
		<html:text property="logInID" size="10" maxlength="15" value="amano" /><br><br>
			<bean:message key="login.password" /><br>
		<html:password property="passWord" size="15" maxlength="20" value="amanopass1" /><br><br>
		<html:submit property="submit" value="ログイン" /><br>
	</html:form>
</body>
</html>