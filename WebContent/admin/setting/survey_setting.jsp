<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">
	function handleTeam() {
		var obj = document.getElementById("team_id");
		var i = obj.selectedIndex; 
		if (i == -1) {
			alert("Please select the team.")
			return false;
		}
		var selectedValue = 0;
		for (;i<obj.length;i++) {			
			if (obj.options[i].selected) {
				selectedValue = selectedValue + ";"+obj.options[i].value;
			}		
		}
		document.getElementById("selected_teams").value=selectedValue;
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
					<h2>SETTING</h2>


				</div>
				<div class="block_content">
<%@ include file="/common/settingSidebar.jsp"%>
					<div class="sidebar_content">
<form action="surveyAction" method="post" id="survey_form"><input
	type="hidden" name="selected_teams" id="selected_teams"> <input
	type="hidden" name="selected_survey_id" id="selected_survey_id"
	value="<s:property value='selected_survey_id' />"> <input
	type="hidden" name="action" id="action" value="save">
	<h3 style="margin-left:10px;padding:5px;">Survey Title : <font style="color: red;"><s:property	value='surveyTitle' /></font></h3>
<table style="font-size:15px;" cellpadding="5px;">
	<tr>
		<td>Survey Start Date:</td>
		<td><input type="text" name="survey_start_date"
			value="<s:property value='survey_start_date'/>"></td>
	</tr>
	<tr>
		<td>Survey End Date:</td>
		<td><input type="text" name="survey_end_date"
			value="<s:property value='survey_end_date'/>"></td>
	</tr>
	<tr>
		<td>Questions:</td>
		<td>
		<select name="questions" size="6">
			<s:iterator value="qList" status="st">
				<option value="<s:property value='id' />"><s:property
					value='name' /></option>
			</s:iterator>
		</select>
		</td>
	</tr>
	<tr>
		<td>Select Teams:</td>
		<td>
		<select name="teams" size="5" multiple id="team_id">
			<s:iterator value="tList" status="st">
				<option value="<s:property value='id' />"
					<s:if test = "%{selected}">selected</s:if>><s:property
					value='name' /></option>
			</s:iterator>
		</select>
		</td>
	</tr>
	<tr>
		<td>Survey For Members:</td>
		<td>
			<s:if test="%{memberSurvey == 1}">
				<input type="radio" name="memberSurvey" value="1" checked  >Yes
				<input type="radio" name="memberSurvey" value="0">No
			</s:if>
			<s:else>
				<input type="radio" name="memberSurvey" value="1" >Yes
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="memberSurvey" value="0" checked>No
			</s:else>
			&nbsp;(<font color='red'>No means the survey will be for team.</font>)			
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button"
			value=" Save " onclick="handleTeam();" class="btn btn-info "></td>
	</tr>
</table>
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