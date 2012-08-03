<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.active.feedback.entities.User;"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script>
	function editQuestion(id) {
		document.getElementById("q_id").value=id;
		document.getElementById("q_form_action").value="edit_exist_q";
		document.getElementById("q_form").submit();
	}
	
	function deleteQuestion(id) {
		document.getElementById("q_id").value=id;
		document.getElementById("q_form_action").value="delete_exist_q";
		document.getElementById("q_form").submit();
	}
	
	function addQuestion() {
		var selectedValue = getSelected();
		if ( selectedValue == 0) {
			alert("Please select an existing question!");
			return false;
		} else {
			document.getElementById("q_id").value=selectedValue;
			document.getElementById("q_form").submit();
		}
	}
	
	function getSelected(){
		var obj = document.getElementById("select_id"); 
		var index = obj.selectedIndex; 
		var selectedValue = obj.options[index].value; 
		if (selectedValue == null || selectedValue == "") {
			selectedValue = 0
		}
		return selectedValue;
		
	}

</script>
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
<form action="questionAction" method="post" id="q_form">
<input type="hidden" name="q_id" id="q_id" >
<input type="hidden" name="q_form_action" id="q_form_action" value="add_exist_q">
<input type="hidden" name="surveyTitle" value="<s:property value='surveyTitle' />" >
<s:bean name="com.active.feedback.bean.QuestionBean" id="q"></s:bean>
<h3 align="center">Survey Title: <font style="color: red;"><s:property value='surveyTitle' /></font></h3>
<fieldset>
<legend style="font-weight: bold;margin-left: 10px;">Add Questions</legend>
<table cellpadding="4px;">
	<tr>
		<td>Create New Question :</td>
		<td><input type="button" value="Create" name="q_create" onclick="self.location.href='question_create.jsp'" class="btn btn-info ">
		<td></td>
	</tr>
	<tr>
		<td>Use An Existing Question:</td>
		<td><select id="select_id">
				<s:iterator value="#q.qList" status="st">
				<option id="<s:property value='id' />" value="<s:property value='id' />"><s:property value="name" /></option>
				</s:iterator>
			</select></td>
			<td><input type="button" name="add_q" value="Add" onclick="addQuestion();" class="btn btn-info "></td>
	</tr>
</table>
</fieldset>
<fieldset>
<legend style="font-weight: bold;margin-left: 10px;">Custom Questions</legend>
<table>		
				<s:iterator value="#q.qCList" status="st" >
				<tr ><td style="padding:5px;">
				<input type="button" value="Edit" name="edit_answer" onclick="editQuestion(<s:property value='id' />);" class="btn btn-info ">&nbsp;
				<input type="button" value="Delete" name="delete_answer" onclick="deleteQuestion(<s:property value='id' />);" class="btn btn-info ">&nbsp;
				</td><td>
				<s:property value="name" />
				<input type="hidden" value="<s:property value='name' />" id="<s:property value='id' />">
				</td></tr>
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