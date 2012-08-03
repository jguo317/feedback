<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<s:form action="login" method="post">
	<table>
		<tr><td><s:textfield label="UserName" name="username"></s:textfield></td></tr>
		<tr><td><s:password label="password" name="password"></s:password></td></tr>
		<tr><td><input type="submit" value="LogIn"></td></tr>
	</table>
</s:form>

</body>
</html>