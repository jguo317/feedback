<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script>
	function changeTeam() {
		var teamSelectedForm = document.getElementById("teamSelectedForm");
		var questionFrm = document.getElementById("questionFrm");
		
		var obj = questionFrm.select_team;
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
	
	function submitSurvey() {
		var questionFrm = document.getElementById("questionFrm");
		
		var obj = questionFrm.select_team;
		var i = obj.selectedIndex; 
		selectedValue = 0;
		for (;i<obj.length;i++) {			
			if (obj.options[i].selected) {
				selectedValue = obj.options[i].value;
				break;
			}		
		}
		questionFrm.team_id.value = selectedValue;
		var obj = questionFrm.select_member;
		var i = obj.selectedIndex; 
		selectedValue = 0;
		if (i > -1) {
			for (;i<obj.length;i++) {			
				if (obj.options[i].selected) {
					selectedValue = obj.options[i].value;
					break;
				}		
			}
		}
		questionFrm.member_id.value = selectedValue;
		questionFrm.hidden_members.value = questionFrm.hidden_members.value + ";" + selectedValue;
		if (selectedValue == 0) {
			if(confirm("do you like to fill the survey again?")){
				var questionFrm = document.getElementById("questionFrm");
				questionFrm.hidden_members.value = ""
			}else{
				closeWin();
			}
		}
	}
	
	function showMsg(){
		if(confirm("do you want to contiune to fill the survey ?")){
			var questionFrm = document.getElementById("questionFrm");
			questionFrm.submit();
		}else{
			closeWin();
		}
	}
	
function closeWin() {
    window.opener=null;
    window.open('','_self');
    window.close();
}
</script>
</head>
<body>
<div id="content"
	style="background: url('../img/hld.jpg') repeat-x scroll 0 0 transparent; min-height: 402px; padding-top: 2px;">
<div class="container"><%@ include file="/common/frontHeader.jsp"%>
<div>
<form action="teamselected" method="post" id="teamSelectedForm">
	<input type="hidden" name="survey_id" value="<s:property value='survey_id'/>"> 
	<input type="hidden" name="team_id" value="0">
</form>

<form action="completeAction" id="questionFrm" method="post" name="questionFrm" onsubmit="submitSurvey();">
	<input type="hidden" name="survey_id" value="<s:property value='survey_id'/>">
	<input type="hidden" name="team_id" value="0"> 
	<input type="hidden" name="member_id" value="0">
	<input type="hidden" name="hidden_members" value="<s:property value='hidden_members'/>" />;
<table border="0"
	style="width: 100%; height: 100%; overflow-y: auto; border-left: 1px solid black; border-right: 1px solid black; border-bottom: 1px solid black;">
	<tr>
		<s:if test="%{memberSurvey == 0}">
		<td width="50%" align="center" style="border-bottom: 1px solid;">
		Please Select Team: <select id="select_id" name="select_team">
			<s:iterator value="tList" status="st">
				<option id="<s:property value='id' />"
					value="<s:property value='id' />"
					<s:if test = "%{id == team_id}">selected</s:if>><s:property
					value="name" /></option>
			</s:iterator>
		</select></td>
		</s:if>		
		<s:else>
		<td width="50%" align="center" style="border-bottom: 1px solid;">
		Select Your Team: <select id="select_id" name="select_team"
			onchange="changeTeam();">
			<s:iterator value="tList" status="st">
				<option id="<s:property value='id' />"
					value="<s:property value='id' />"
					<s:if test = "%{id == team_id}">selected</s:if>><s:property
					value="name" /></option>
			</s:iterator>
		</select></td>
		<td width="50%" align="center" style="border-bottom: 1px solid;">
		Select the member you like to comment: <select id="select_id" name="select_member">
			<s:iterator value="uList" status="st">
				<option id="<s:property value='id' />"
					value="<s:property value='id' />"><s:property
					value="fname" />&nbsp;<s:property value="lname" /></option>
			</s:iterator>
		</select></td>
		</s:else>
	</tr>
	<tr>
		<td style="width: 100%; height: 798px; vertical-align: top;"
			colspan="4">
		<div class="content">
		<ol style="padding-left: 20px;">
			<s:iterator value="qList" status="st">
				<li style="margin-top: 8px;"><s:property value="name" /></li>
				<s:set name="qt_name" value="qt.name" />
				<s:set name="q_id" value="id" />
				<s:if test="%{#qt_name=='text'}">
					<textarea name="text_<s:property value='id'/>" ></textarea>
				</s:if>
				<s:elseif test="%{#qt_name=='radio'}">
					<s:iterator value="qdList" status="st">
						<input type="<s:property value='qt.name'/>"
							name="radio_<s:property value='#q_id'/>"
							value="<s:property value='id'/>">
						<s:property value="value" />
						<br />
					</s:iterator>
				</s:elseif>
				<s:else>
					<s:iterator value="qdList" status="st">
						<input type="<s:property value='qt.name'/>"
							name="checkbox_<s:property value='id'/>"
							value="<s:property value='id'/>">
						<s:property value="value" />
						<br />
					</s:iterator>
				</s:else>
			</s:iterator>
		</ol>
		</div>
		</td>
	</tr>
</table>
<div align="center" style="margin-top: 10px; margin-bottom:0px;">
	<input type="submit" value="Save & Continue" onclick="continueSurvey()">&nbsp;&nbsp;&nbsp;&nbsp; 
	<input type="submit" id="close" value=" Close " onclick="closeWin()">
</div>
</form>
</div>
<!--/block withsidebar--> <%@ include file="/common/footer.jsp"%>
</div>
<!--/.container --></div>
<!--/.content -->
</body>
</html>