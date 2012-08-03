<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.active.feedback.test.action.*,java.util.*,com.opensymphony.xwork2.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Object</title>
</head>
<body>
<%
TestObjectService tos = new TestObjectService();
tos.init();
TestObject to = (TestObject)tos.getTo();
%>
<table>
	<tr><td>Test1:<%=to.getTest1() %></td></tr>
	<tr><td>Test2:<%=to.getTest2() %></td></tr>
</table>
</body>
</html>