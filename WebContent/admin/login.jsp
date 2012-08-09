<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">
	if (top.location.href.indexOf("/360feedback/admin/login.jsp") <= 0)
		top.location.href = "/360feedback/admin/login.jsp";
</script>
</head>
<body>
<div 	style="padding-top:50px;">
<div class="container"><%@ include file="/common/frontHeader.jsp"%>
<div>
<form action="login" method="post">
<table align="center">
	<tr>
		<td>Username :</td>
		<td><input type="text" name="username"  size="20"></td>
	</tr>
	<tr>
		<td>Password :</td>
		<td><input type="password" name="password" size="20"></td>
	</tr>
	<tr align="center" >
		<td colspan="2"><input type="submit" value="Login" onclick="self.location=’admin.jsp’;" ></td>
	</tr>
</table>
</form>
</div>
<!--/block withsidebar--> <%@ include file="/common/footer.jsp"%>
</div>
<!--/.container --></div>
<!--/.content -->
</body>
</html>