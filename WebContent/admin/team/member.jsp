<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script>
	var userId = "<%=session.getAttribute("user_id") %>".toString();
	if (userId == 'null') self.location.href="../login.jsp";
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
<%@ include file="/common/teamSidebar.jsp"%>
					<div class="sidebar_content">
<form action="memberADUAction" method="post" id="m_form">
<input type="hidden" name="teamId" value="<s:property value='teamId' />" />
<input type="hidden" name="action" id="action" value="add" />
<input type="hidden" name="memberId" id="memberId" value="0" />
<h3 align="center">Team Name: <font style="color: red;"><s:property value='teamName' /></font></h3>
<fieldset>
<legend style="font-weight: bold;margin-left: 10px;">Create Team Member</legend>
<table>
	<tr>
		<td>Create New Member :</td>
		<td><input type="submit" value="Create" name="m_create" class="btn btn-info "></td>
		<td></td>		
	</tr>
</table>
</fieldset>
<fieldset>
<legend style="font-weight: bold;margin-left: 10px;">Team Members</legend>
	<table>
		<tr>
			<th>Name</th>
			<th></th>
			<th>Email</th>
		</tr>
		<s:iterator value="mList" status="st">
			<tr>
				<td><s:property value="fname" /> <s:property value="lname" /> </td>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td><s:property value="email" /></td>
			</tr>
		</s:iterator>
	</table>
</fieldset>
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