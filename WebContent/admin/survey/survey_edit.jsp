<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.active.feedback.entities.User;"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>

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
<form action="surveyEditAction" method="post">
	<input type="hidden" name="surveyId" value="<s:property value='surveyId' />" />
	<input type="hidden" name="action" value="edit" />
	<div class="form-horizontal">
<fieldset class="inputs">
<legend>Survey</legend>
		<table style="margin-top: 20px;">
			<tr>
				<td>Survey Title:</td>
				<td><input type="text" name="surveyTitle" value="<s:property value='surveyTitle' />"></td>
				<td><input type="submit" name="update_survey" value="Update" class="btn btn-info " /></td>
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