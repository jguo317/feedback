<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">

	function changeTeam() {
		var teamSelectedForm = document.getElementById("teamSelectedForm");
		var reportFrm = document.getElementById("report_team_form");
		
		var obj = reportFrm.select_team;
		var i = obj.selectedIndex; 
		selectedValue = 0;
		for (;i<obj.length;i++) {			
			if (obj.options[i].selected) {
				selectedValue = obj.options[i].value;
				break;
			}		
		}
		teamSelectedForm.team_id.value = selectedValue;
		teamSelectedForm.submit();
	}
	
	function search() {
		var reportFrm = document.getElementById("report_team_form");
		
		var obj = reportFrm.select_team;
		var i = obj.selectedIndex; 
		selectedValue = 0;
		for (;i<obj.length;i++) {			
			if (obj.options[i].selected) {
				selectedValue = obj.options[i].value;
				break;
			}		
		}
		reportFrm.team_id.value = selectedValue;
		
		var obj = reportFrm.select_member;
		var i = obj.selectedIndex; 
		selectedValue = 0;
		for (;i<obj.length;i++) {			
			if (obj.options[i].selected) {
				selectedValue = obj.options[i].value;
				break;
			}		
		}
		reportFrm.member_id.value = selectedValue;
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

<form action="reportTeamAction" method="post" id="teamSelectedForm">
	<input type="hidden" name="selected_survey_id" value="<s:property value='selected_survey_id'/>"> 
	<input type="hidden" name="team_id" value="0">
</form>
<form action="reportAction" method="post" id="report_team_form" onsubmit="search();" >
<input type="hidden" name="selected_survey_id" value="<s:property value='selected_survey_id'/>">
<input type="hidden" name="team_id" value="0"> 
<input type="hidden" name="member_id" value="0">
<div class="form-horizontal">
<fieldset class="inputs">
<legend>Report</legend>
<table style="border-bottom: 0px">
	<s:if test="%{memberSurvey == 0}">
		<tr>
		<td> Please Select Team: 
		<select id="select_id" name="select_team">
			<option id="0" value="0"></option>
			<s:iterator value="tList" status="st">
				<option id="<s:property value='id' />"
					value="<s:property value='id' />"
					<s:if test = "%{id == team_id}">selected</s:if>><s:property
					value="name" /></option>
			</s:iterator>
		</select>
		<input type="hidden" name="select_member" value=0>
		</td>
		</tr>
	</s:if>		
	<s:else>
		<tr>
		<td>Please Select Team: </td>
		<td>
		<select id="select_id" name="select_team" onchange="changeTeam();">
			<option id="0" value="0"></option>
			<s:iterator value="tList" status="st">
				<option id="<s:property value='id' />"
					value="<s:property value='id' />"
					<s:if test = "%{id == team_id}">selected</s:if>><s:property
					value="name" /></option>
			</s:iterator>
		</select>
		</td></tr><tr>
		<td> Please Select Member: </td>
		<td>
		<select id="select_id" name="select_member">
			<s:iterator value="uList" status="st">
				<option id="<s:property value='id' />"
					value="<s:property value='id' />"><s:property
					value="fname" />&nbsp;<s:property value="lname" /></option>
			</s:iterator>
		</select></td></tr>
	</s:else>
	<tr><td align="center" colspan=2><input type="submit" value="Search" class="btn btn-info "></td></tr>
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