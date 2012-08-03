<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.opensymphony.xwork2.util.*,com.active.feedback.test.been.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Object</title>
</head>
<body>

<form>
	<s:bean name="com.active.feedback.test.been.QuestionTest" id="qt"></s:bean>
	<table>
	<s:iterator value="#qt.qList" status = "q">
	<s:if test="%{q_type == 'radio'}">	
		<tr><td>
			<s:property value="q_name" />
		</td></tr>
		<s:iterator value="qdList" status="qd">
		<tr><td>
			<input type="radio" value="<s:property value='qd_name' />"><s:property value="qd_name" />
		</td></tr>
		</s:iterator>
	</s:if>	
	<s:elseif test="%{q_type == 'checkbox'}">
		<tr><td>
			<s:property value="q_name" />
		</td></tr>
		<s:iterator value="qdList" status="qd">
		<tr><td>
			<input type="checkbox" value="qd_name"><s:property value="qd_name" />
		</td></tr>
		</s:iterator>	
	</s:elseif>
	<s:else>
		<tr><td>
			<s:property value="q_name" />
		</td></tr>
		<tr><td>
			<input type="text" value=""><s:property value="qd_name" />
		</td></tr>
	</s:else>
	
	
	</s:iterator>
	</table>

</form>
</body>
</html>