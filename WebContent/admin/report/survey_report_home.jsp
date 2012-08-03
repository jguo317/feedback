<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">

	function selectSurvey() {
		var obj = document.getElementById("selected_survey");
		var i = obj.selectedIndex; 
		selectedValue = 0;
		for (;i<obj.length;i++) {			
			if (obj.options[i].selected) {
				selectedValue = obj.options[i].value;
				break;
			}		
		}
		document.getElementById("selected_survey_id").value=selectedValue;
		document.getElementById("report_form").submit();
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
					<h2>REPORT</h2>


				</div>
				<div class="block_content">
<%@ include file="/common/reportSidebar.jsp"%>
					<div class="sidebar_content">
<s:bean name="com.active.feedback.bean.SurveyBean" id="survey"></s:bean>
<form action="reportTeamAction" method="post" id="report_form">
<input type="hidden" name="selected_survey_id" id="selected_survey_id">
<input type="hidden" name="action" id="action" value="select">
<div class="form-horizontal">
<fieldset class="inputs">
<legend>Report</legend>
<table>
	<tr>
		<td><b>Please Select An Existing Survey : </b></td>
		<td>
			<select id="selected_survey" onchange="selectSurvey();">
				<option/>
				<s:iterator value="#survey.surveyList" status="st">
				<option id="<s:property value='id' />" value="<s:property value='id' />" ><s:property value="title" /></option>
				</s:iterator>
			</select>
		</td>
	</tr>
</table>
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
</body>
</html>