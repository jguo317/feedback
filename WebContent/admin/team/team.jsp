<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">
	function selectTeam() {
		var obj = document.getElementById("selected_team");
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
		document.getElementById("selected_team_id").value=selectedValue;
		document.getElementById("selected_team_name").value=selectedLabel;
		document.getElementById("team_form").submit();
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
					<h2>TEAM</h2>
				</div>
				<div class="block_content">
<%@ include file="/common/teamSidebar.jsp"%>
					<div class="sidebar_content">

<s:bean name="com.active.feedback.bean.TeamBean" id="team"></s:bean>

<form action="teamSCEAction" method="post" id="team_form">
<input type="hidden" name="teamId" value="" id="selected_team_id" /> 
<input type="hidden" name="teamName" value="" id="selected_team_name" />
<input type="hidden" name="action" id="action" value="select" />

<div class="form-horizontal">
<fieldset class="inputs">
<legend>Team</legend>

<div class="select control-group required">
<label class=" control-label" >Use Existing Teams:</label><div class="controls">
<select id="selected_team" onchange="selectTeam();">
			<option />
			<s:iterator value="#team.tList" status="st">
				<option id="<s:property value='id' />"
					value="<s:property value='id' />"
					label="<s:property value='name' />"><s:property
					value="name" /></option>
			</s:iterator>
		</select>

</div></div>

</fieldset>

<fieldset  style="padding: 10px 20px; margin-top: 18px; background-color: #f5f5f5; border-top: 1px solid #e5e5e5;">
<div class="select control-group">
	<a class="btn btn-info " href="team_create.jsp">Create New Team</a>
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