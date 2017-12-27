<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"
		 import="salemanagement.UserDataBeans" %>
<%@ taglib 	uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib 	uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib 	uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
	HttpSession hs = request.getSession();
	String login = (String)hs.getAttribute("login");
	int ac = (int)hs.getAttribute("ac");
	UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
	String mode = (String)request.getAttribute("mode");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee List</title>
</head>
<body>
	<h3>販売管理システム</h3>
	<html:form action="do/LogOutAction" method="Post">
		<html:submit property="logout" value="ログアウト"/>
	</html:form>
	ログインユーザー：
	<bean:write name="login" scope="session" />　<br>
	ホーム　基本設定メニュー<br><br>
	<html:form action="do/EmpListReturnAction" method="post">
		<input type="hidden" name="ac" value="<%=ac%>">
		<html:submit property="btnBack" value="戻る" /><br><br>
	</html:form>
	<html:form action="do/EmpSearchAction" method="Post">
		<input type="hidden" name="ac" value="<%=ac%>">
		名前検索　
		<html:text property="txtNameSearch" value="" />　
		所属　
		<html:select property="dlstBranchSearch">
			<html:optionsCollection name="LogInForm" property="dBranchList" />
		</html:select>
		部署　
		<html:select property="dlstDepSearch">
			<html:optionsCollection name="LogInForm" property="dDepartmentList" />
		</html:select>
		<label><html:radio property="rdoDelete" value="1" />削除</label>　
		<html:submit property="btnSearch" value="検索" /><br>
		（名前、かなの部分一致）<br><br>
	</html:form>
	<html:form action="do/EmpSelectAction" method="Post">
		<table border="1">
			<tr>
				<td></td>
				<td>No</td>
				<td>従業員名</td>
				<td>所属</td>
				<td>部署</td>
				<td>上司</td>
			</tr>
			<logic:iterate id="grid" name="<%=mode%>" property="grdList">
				<tr>
					<td><html:link action="do/EmpSelectAction" indexed="true"
							indexId="gridIndex" paramId="empSlNo" paramName="grid"
							paramProperty="empNo">
							選択
							<html:param name="ac"><%=ac%></html:param>
						</html:link></td>
					<td><bean:write name="grid" property="empNo" /></td>
					<td><bean:write name="grid" property="empName" /></td>
					<td><bean:write name="grid" property="empBranch" /></td>
					<td><bean:write name="grid" property="empDepartment" /></td>
					<td><bean:write name="grid" property="empBossName" /></td>
				</tr>
			</logic:iterate>
		</table>
	</html:form>

</body>
</html>