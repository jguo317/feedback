<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<body>
<div id="content">
<div class="container"><%@ include file="/common/header.jsp"%>
<div class="block withsidebar">
<div class="block_head">
<div class="bheadl"></div>
<div class="bheadr"></div>
<h2>Survey</h2>

</div>
<div class="block_content"><%@ include
	file="/common/teamSidebar.jsp"%>
<div class="sidebar_content">
<form action="teamSCEAction" method="post"><input type="hidden"
	name="teamId" value="<s:property value='teamId' />" /> <input
	type="hidden" name="action" id="action" value="edit" />
	<div  class="select control-group required">
		<div class="form-horizontal">
<fieldset class="inputs">
<legend>Team</legend>
		<table>
			<tr>
				<td>Team Name:</td>
				<td><input type="text" name="teamName"	value="<s:property value='teamName' />"></td>
				<td><input type="submit"	name="create_team" value="Update" class="btn btn-info " /></td>
			</tr>
		</table>
		</fieldset>
</div>
		</div>
</form>
</div>

</div>
<div class="bendl"></div>
<div class="bendr"></div>

</div>
<!--/block withsidebar--> <%@ include file="/common/footer.jsp"%>
</div>
<!--/.container --></div>

</body>
</html>