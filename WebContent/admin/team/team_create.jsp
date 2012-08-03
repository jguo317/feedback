<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">
		var userId = "<%=session.getAttribute("user_id")%>
	".toString();
	if (userId == 'null')
		self.location.href = "login.jsp";
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
					
<form action="teamSCEAction" method="post">
	<input type="hidden" name="teamId" value="0" />
	<input type="hidden" name="action" id="action" value="create" />
 <div class="select control-group required">
		<fieldset class="inputs">
		<legend>New Team</legend>
		<table>
			<tr>
				<td><label class=" control-label">Team Name:</label></td>
				<td><div class="controls"><input type="text" value="" name="teamName" maxlength="255" ></div></td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="form-actions"  style="padding: 10px 20px; margin-top: 18px; background-color: #f5f5f5; border-top: 1px solid #e5e5e5;">
<input type="submit" value="Create" name="create_team" class="btn btn-info"></fieldset>
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