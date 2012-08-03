<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
					<h2>SETTING</h2>
				</div>
				<div class="block_content">
<%@ include file="/common/settingSidebar.jsp"%>
					<div class="sidebar_content">
<form action="sendEmailAction">
<input type="hidden" name="survey_url" value="/360feedback/front/index.jsp?survey_id=<s:property value='selected_survey_id'/>" >
<input type="hidden" name="survey_id" value="<s:property value='selected_survey_id'/>" >
		<fieldset>
		<table cellpadding="5px;">
			<tr>
				<td style="font-size:15px;">Click <a href="/360feedback/front/index.jsp?survey_id=<s:property value='selected_survey_id'/>" target="_blank">here</a> to review your survey!</td>
			</tr>
			<tr>
				<td><input type="submit" value="Send Emails" class="btn btn-info "></td>
			</tr>
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
	<!--/.content -->
</body>
</html>