<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.active.feedback.entities.User;"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">

	function submit(){
		document.getElementById("answer_form").submit();
 	}

</script>
</head>
<body onload="submit();">
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
<form action="answerAction" id="answer_form">
<input type="hidden" name="q_id" value="<s:property value='q_id' />">
<input type="hidden" name="answer_action" value="add_answer">
<table>
<tr><td>
<fieldset>

	<table id="answer_table">
		<tr>
			<td colspan="3" align="center">Add Question Answer</td>			
		</tr>
		
		<tr>
			<td><input type="text" value="" name="answer" id="answer"></td>
			<td><input type="button" value=" Add " name="add_answer" id="add_answer" onclick="addAnswer();"></td>
			<td><input type="button" value=" Update " name="update_answer" id="update_answer" onclick="updateAnswer();"></td>
		</tr>		
	</table>
</fieldset>
</td></tr>
<tr><td></td></tr>
<tr><td align="center">
<fieldset>
	<table id="answer_table">
		<tr>
			<td align="center">Question Answer</td>			
		</tr>			
	</table>
</fieldset>
</td></tr>
<tr><td></td></tr>
<tr><td align="center"><input type="submit" value=" OK "></td></tr>
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



</body>
</html>