<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.active.feedback.test.been.QuestionTest,com.active.feedback.test.been.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Object</title>
</head>
<body>
<%
	QuestionTest qt = new QuestionTest();
qt.init();
List<Question> qList = qt.getQ();
%>
<form>
	<table>
		<%
		for(int i =0; i < qList.size(); i++) {
			Question q = qList.get(i);
			if (q.getQ_type().equals("text")) {
		
			%>
		<tr><td>
			<%=q.getQ_name() %>
		</td></tr><tr></tr>
		<tr><td>
			<input type="text" name="<%=q.getQ_name() %>">
		</td></tr><tr></tr>
		<%} else { %>
		<tr><td>
			<%=q.getQ_name() %>
		</td></tr><tr></tr>
		<%List<QuestionData> qdList = q.getQdList(); 
			for (int j = 0; j < qdList.size(); j++) {
				QuestionData qd = qdList.get(j);
		%>
		<tr><td>
			<input type="<%=q.getQ_type() %>" name="<%=q.getQ_type() %><%=i %>"><%=qd.getQd_name() %>
		</td></tr>
		
		<tr></tr>
		<%}}} %>
		
	</table>
</form>
</body>
</html>