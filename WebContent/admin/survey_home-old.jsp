<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page
	import="com.active.feedback.dao.SurveyDao,com.active.feedback.entities.Survey,com.active.feedback.impl.SurveyDaoImpl,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey Home</title>
</head>
<body bgcolor="#E4E5E6">

<form>
	<table align="center">
		<tr><td>
			<input type="button" value="Create New Survey" name="create_survey" onclick="self.location.href='survey_create.jsp'">
		</td></tr>
		<%
			SurveyDao sDao = new SurveyDaoImpl();
			int userId = 1;
			if (session.getAttribute("user_id") != null) 
				userId = (Integer)session.getAttribute("user_id");
			List<Survey> surveyList = sDao.getAllSurveysByUserId(userId);
		%>
			<tr><td>&nbsp;</td></tr>	
			<tr>
				<td><b>Please Select An Existing Survey : </b></td>
			</tr>
			<tr>
			<td>
			 <select id="select_id">
				<%
					for (Survey survey : surveyList) {
				%>
				<option id="<%=survey.getId()%>"><%=survey.getTitle()%></option>
				<%
					}
				%>
				</select>
			</td>
			</tr>
	</table>
</form>

</body>
</html>