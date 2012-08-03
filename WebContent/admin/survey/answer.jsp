<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">
	function addAnswer() {
		document.getElementById("answer_action").value = "add_answer";
		document.getElementById("answer_form").submit();
	}
	function deleteAnswer(id) {
		document.getElementById("answer_action").value = "delete_answer";
		document.getElementById("qt_id").value = id;
		document.getElementById("answer_form").submit();
	}
	
	function editAnswer(id) {
		document.getElementById("update_answer").disabled = false;
		document.getElementById("add_answer").disabled = true;

		document.getElementById("qt_id").value = id;

		document.getElementById("answer").value = document.getElementById(id).value;
	}
	
	function updateAnswer() {
		document.getElementById("answer_action").value = "update_answer";
		document.getElementById("update_answer").disabled = true;
		document.getElementById("add_answer").disabled = false;
		
		document.getElementById("answer_form").submit();
	}
	
	function clickOK() {
		document.getElementById("answer_action").value = "ok";
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
					<h2>Survey</h2>

				</div>
				<div class="block_content">
<%@ include file="/common/surveySidebar.jsp"%>
					<div class="sidebar_content">
<form action="answerAction" id="answer_form" method="post">
<input type="hidden" name="q_id" value="<s:property value='q_id' />">
<input type="hidden" name="answer_action" id="answer_action" value="add_answer">
<input type="hidden" name="qt_id" value="" id="qt_id"/>

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
			<td><input type="button" value=" Update " name="update_answer" id="update_answer" disabled onclick="updateAnswer();"></td>
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
		
		
		<tr>
			<td align="center">	
				<table>		
				<s:iterator value="answerList" status="st" >
				<tr><td>
				<input type="button" value="Edit" name="edit_answer" onclick="editAnswer(<s:property value='id' />);">&nbsp;
				<input type="button" value="Delete" name="delete_answer" onclick="deleteAnswer(<s:property value='id' />);">&nbsp;
				</td><td>
				<s:property value="value" />
				<input type="hidden" value="<s:property value='value' />" id="<s:property value='id' />">
				</td></tr>
				</s:iterator>	
				</table>	
			</td>	
		</tr>
		
	</table>
</fieldset>
</td></tr>
<tr><td></td></tr>
<tr><td align="center"><input type="submit" value=" OK " onclick="clickOK();"></td></tr>
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