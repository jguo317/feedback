 	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.active.feedback.entities.User;"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">
	
	function selectSurvey() {
		var obj = document.getElementById("selected_survey");
		var i = obj.selectedIndex; 
		selectedValue = 0;
		selectedLabel = "";
		for (;i<obj.length;i++) {			
			if (obj.options[i].selected) {
				selectedValue = obj.options[i].value;
				selectedLabel = obj.options[i].label;
				break;
			}		
		}
		document.getElementById("selected_survey_id").value=selectedValue;
		document.getElementById("selected_survey_title").value=selectedLabel;
		document.getElementById("survey_form").submit();
	}
</script>
</head>
<body>
	<div id="content">
		<div class="container">
			<%@ include file="/common/header.jsp"%>
			<div class="block withsidebar">
				<div class="block_head">
					<div class="bheadl"></div>
					<div class="bheadr"></div>
					<h2>Survey</h2>
				</div>
				<div class="block_content">
<%@ include file="/common/surveySidebar.jsp"%>
					<div class="sidebar_content">
<s:bean name="com.active.feedback.bean.SurveyBean" id="survey"></s:bean>
<form action="surveyEditAction" method="post" id="survey_form">
<input type="hidden" name="surveyId" value="" id="selected_survey_id" /> 
<input type="hidden" name="surveyTitle" value="" id="selected_survey_title" />
<input type="hidden" name="action" value="select" />
<div class="form-horizontal">
<fieldset class="inputs">
<legend>Survey</legend>
<div class="select control-group required">
<label class=" control-label" >Use Existing Survey:</label><div class="controls">
<select id="selected_survey" onchange="selectSurvey();">
			<option />
			<s:iterator value="#survey.surveyList" status="st">
				<option id="<s:property value='id' />"
					value="<s:property value='id' />"
					label="<s:property value='title' />"><s:property
					value="title" /></option>
			</s:iterator>
		</select>
</div></div>
</fieldset>

<fieldset  style="padding: 10px 20px; margin-top: 18px; background-color: #f5f5f5; border-top: 1px solid #e5e5e5;">
<div class="select control-group">
	<a class="btn btn-info " href="survey_create.jsp">Create New Survey</a>
</div>
</fieldset>
</div>
</form>
					</div>

				</div>
				<div class="bendl"></div>
				<div class="bendr"></div>

			</div>
			<!--/block withsidebar-->
			<%@ include file="/common/footer.jsp"%>
		</div>
		<!--/.container -->
	</div>
	<!--/.content -->
</body>
</html>