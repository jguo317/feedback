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
		<div class="container" style="">
			<%@ include file="/common/frontHeader.jsp"%>
			<div >
<table  height="860px" width="100%" border="0"  >
	<tr>
		<td width="15%"></td>
		<td  align="center">
				Thanks for using	our application!<br>
				Please click <a href="index.jsp?survey_id=<s:property value='survey_id'/>">here</a> to continue.
		</td>
		<td width="15%"></td>
	</tr>
</table>
</div>
			<!--/block withsidebar-->
			<%@ include file="/common/footer.jsp"%>
		</div>
		<!--/.container -->
	</div>
	<!--/.content -->
</body>
</html>