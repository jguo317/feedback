<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String strSurveyId = request.getParameter("survey_id");
	int surveyId = 0;
	if (strSurveyId != null && !"".equals(strSurveyId)) {
		surveyId = Integer.parseInt(strSurveyId);
	}
%>
<html>
<head>
<script type="text/javascript">

	function submit(){
		var form=document.getElementById("form1");
		form.submit();
		 	}

</script>
<title>360feedback</title>
</head>
<body onload="submit();">
<div align="center">
<form action="teamselected" method="post" id="form1"><input
	type="hidden" name="survey_id" id="survey_id" value="<%=surveyId%>">
	<input type="hidden" name="team_id" value="0"></form>
</div>
</body>
</html>