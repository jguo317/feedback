<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.active.feedback.entities.User;"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script>

	function showNext() {
		var radioButton = getRadioValue("qt_radio");
		if (radioButton == 3) {
			document.getElementById("next").disabled = true;
				document.getElementById("save").disabled=false;
		} else {
			document.getElementById("next").disabled = false;
			document.getElementById("save").disabled=true;
		}
	}
	
	function getRadioValue(objName) { 
		var objs = document.getElementsByName(objName); 
		for(var i=0; i<objs.length; i++) { 
			if(objs[i].checked) return objs[i].value; 
		} 
		return 0; 
	} 
	
	function clickNext() {
		if (validate()) {
			document.getElementById("q_form_action").value = "edit_next";
			document.getElementById("q_type").value = getRadioValue("qt_radio");
			document.getElementById("q_from").submit();
		}
	}
	function clickSave() {
		if (validate()) {
			document.getElementById("q_form_action").value = "edit_save";
			document.getElementById("q_type").value = getRadioValue("qt_radio");
			alert(document.getElementById("q_type").value)
			alert(document.getElementById("q_id").value)
			document.getElementById("q_from").submit();
		}
	}
	
	function validate() {
		var radioButton = getRadioValue("qt_radio");
		if (radioButton == 0) {
			alert("Please select a question type!");
			return false;
		}
		var q_name = document.getElementById("q_name");
		if (q_name.value == "") {
			alert("Please enter a question name!")
			return false;
		}
		return true;
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
	<form action="questionAction" method="post" id="q_from">
		<s:hidden name="q_form_action" value="save" id="q_form_action"></s:hidden>
		<s:hidden name="q_type" value="" id="q_type"></s:hidden>
		<input type="hidden" name="q_id" id="q_id" value="<s:property value='q_id' />" >
		<table>
			<tr><td>
				<fieldset>
				<s:bean name="com.active.feedback.bean.QuestionTypeBean" id="bean"></s:bean>
				<table id="q_table">
					<tr>
						<td>Question Type:</td>
						
						<td>
							<table>
							<s:iterator value="#bean.qtList" status = "qt">
							<tr>
							<td>
								<s:if test="%{q_type==id}">
								<input type="radio" id="<s:property value='id'/>" value="<s:property value='id'/>" name="qt_radio" checked onclick="showNext();"><s:property value="name" />
								</s:if>
								<s:else>
								<input type="radio" id="<s:property value='id'/>" value="<s:property value='id'/>" name="qt_radio" onclick="showNext();"><s:property value="name" />
								</s:else>			
								
							</td>
							</tr>
							</s:iterator>
							</table>
							
						</td>
						
					</tr>
					<tr>
						<td>Question Name:</td>
						<td><input type="text" value="<s:property value='q_name' />" name="q_name" id="q_name"></td>
					</tr>
				</table>
				
				</fieldset>
			</td></tr>
			<tr><td>
				<table align="right">
					<tr><td><input type="button" value="Next" id="next" onclick="clickNext();"></td>
					<td><input type="button" value="Save" id="save" onclick="clickSave();"></td></tr>
				</table>
			</td></tr>
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