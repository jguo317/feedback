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
					
<form action="surveyCreateAction" method="post">
	<div class="select control-group required">
		<fieldset class="inputs">
		<legend>New Survey</legend>
		<div class="string control-group optional stringish">
		<table>
			<tr>
				<td><label class=" control-label">Survey Title:</label></td>
				<td><div class="controls"><input type="text" value="" name="surveyTitle" maxlength="255" >
</div></td>
			</tr>
		</table>
		
		</div>

	</fieldset>
	<fieldset class="form-actions">
<input type="submit" value="Create" name="create_survey" class="btn btn-info "></fieldset>
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