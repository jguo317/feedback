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
					<h2>REPORT</h2>
				</div>
				<div class="block_content">
<%@ include file="/common/reportSidebar.jsp"%>
					<div class="sidebar_content">
					<div class="form-horizontal">
<fieldset class="inputs">
<legend>Report</legend>
<div style="overflow-y: auto; height: 100%; width: 100%; vertical-align: top; font-size: 16px;">
<ol>
	<s:iterator value="srList" status="st">
		<li><s:property value="q_name" /></li><br>
		<s:if test="%{q_type=='text'}">
			<s:iterator value="answerList" status="st">
				<label style="font-weight: bold;color: red;"><s:property /></label><br>
			</s:iterator>
			<br>
		</s:if>
		<s:else>
			<s:iterator value="qdList" status="st">
				<s:if test="%{q_type=='checkbox'}">
					<input type="checkbox" disabled="disabled">&nbsp;<s:property value="value" /> - <label style="font-weight: bold;color: red;"><s:property	value="count" /></label><br>	
				</s:if>
				<s:else>
					<input type="radio" disabled="disabled">&nbsp; <s:property value="value" /> - <label style="font-weight: bold;color: red;"><s:property 	value="count" /></label>	<br>
				</s:else>
			</s:iterator>
			<br>
		</s:else>
	</s:iterator>
</ol>
</div>
</fieldset>
</div>
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