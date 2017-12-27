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
	String mode = (String)hs.getAttribute("mode");
	if(mode == null){mode = "";}
	String fname = "";
	switch (mode) {
		case "NameSearch":
			fname = "EmpSelectForm"; break;
		case "BossSearch":
			fname = "EmpSelectForm"; break;
		case "PassWord":
			fname = "EmpPWForm"; break;
		default:
			fname = "EmpForm"; break;
	}
	boolean read = true;
	String pass = (String)session.getAttribute("password");
	if(pass == null){ pass = ""; read = false;}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<link rel="stylesheet" type="text/css" href="/SaleManagementSystem/css/employee.css" />
<head>
<title>Employee</title>
</head>
<body>
	<script language="JavaScript" type="text/javascript">
		function searchname() {
			EmpSearchNameForm.submit();
		}
		function searchboss() {
			EmpSearchBossForm.submit();
		}
		function newpassword() {
			EmpPWForm.submit();
		}
		function empdelete() {
		    if (window.confirm("削除してもよろしいですか？")) {
		        return true;
		    } else {
		        return false;
		    }
		}
	</script>
	<h3>販売管理システム</h3>
	<html:form action="do/LogOutAction" method="Post">
		<html:submit property="logout" value="ログアウト"/>
	</html:form>
	ログインユーザー：
	<bean:write name="login" scope="session" /><br>
	ホーム　基本設定メニュー<br><br>
	<html:form action="do/EmployeeAction" method="Post">
		<input type="hidden" name="ac"  value="<%=ac%>">
		<div class="column1">No</div>
		<html:text name="<%=fname%>" property="txtEmpNo" size="3" readonly="<%=read%>" />
		<br>
		<div class="column1">名前</div>
		<html:text name="<%=fname%>" property="txtName" readonly="<%=read%>" />
		<html:button property="btnset" onclick="searchname()" disabled="<%=read%>">検索</html:button>
		<br>
		<div class="column1">名前(カナ)</div>
		<html:text name="<%=fname%>" property="txtKanaName" readonly="<%=read%>" />
		<% if(pass.equals("on")){ %>
			<div class="column2">新パスワード</div>
			<html:password name="<%=fname%>" property="txtLogInPW1" size="10" />
		<% } %>
		<br>
		<div class="column1">LogInID</div>
		<html:text name="<%=fname%>" property="txtLogInID" readonly="<%=read%>" />
		<% if(pass.equals("on")){ %>
			<div class="point-top">
				<div class="column2">確認</div>
				<html:password name="<%=fname%>" property="txtLogInPW2" size="10" />
			</div>
		<% } %>
		<br>
		<div class="column1">メール</div>
		<html:text name="<%=fname%>" property="txtMail" readonly="<%=read%>" />
		<br>
		<div class="column1">所属</div>
		<html:select name="<%=fname%>" property="dlstBranch" disabled="<%=read%>">
			<html:optionsCollection name="LogInForm" property="dBranchList" />
		</html:select>
		<% if(pass.equals("on")){ %>
			<div class="button">
				<html:submit property="btnPWResist" value="登録" />
				<div class="button-space">
				<html:submit property="btnClose" value="閉じる" />
				</div>
			</div>
		<% } %>
		<br>
		<div class="column1">部署</div>
		<html:select name="<%=fname%>" property="dlstDepartment" disabled="<%=read%>">
			<html:optionsCollection name="LogInForm" property="dDepartmentList" />
		</html:select>
		<% if(pass.equals("on")){ %>
			<div class="button">
				<html:errors property="txtLogInPW1" />
				<html:errors property="txtLogInPW2" />
			</div>
		<% } %>
		<br>
		<div class="column1">上司</div>
		<html:text name="<%=fname%>" property="txtBoss" readonly="true" />
		<html:button property="btnBossSearch" onclick="searchboss()" disabled="<%=read%>">検索</html:button>
		<br>
		<div class="column1">権限</div>
		<logic:iterate id="roleindex" name="LogInForm" property="chRoleList">
			<html:multibox name="<%=fname%>" property="chRole" value="${roleindex.value}" disabled="<%=read%>" />
			<bean:write name="roleindex" property="label" />
		</logic:iterate>
		<br>
		<div class="column1">パスワード</div>
		<html:text name="<%=fname%>" property="txtPW" readonly="true" />
		<html:button property="btnLogInPW" onclick="newpassword()" disabled="<%=read%>">パスワード登録</html:button>
		<br><br>
		<div class=button-bottom>
			<html:submit property="btnRegist" value="登録" disabled="<%=read%>" />
			<div class="button-space">
			<html:submit property="btnDelete" value="削除" onclick="return empdelete();" disabled="<%=read%>" />
			</div><br>
		</div>
		<% if(!pass.equals("on")){ %><html:errors /><% } %>
	</html:form>
	<html:form action="do/EmpSearchNameAction" method="Post">
		<input type="hidden" name="ac"  value="<%=ac%>">
	</html:form>
	<html:form action="do/EmpSearchBossAction" method="Post">
		<input type="hidden" name="ac"  value="<%=ac%>">
	</html:form>
	<html:form action="do/EmpPassWordAction" method="Post">
		<input type="hidden" name="ac"  value="<%=ac%>">
	</html:form>
</body>
</html>