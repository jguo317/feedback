<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.active.feedback.entities.User"%>
<%
	User user = (User) session.getAttribute("user");
	String username = "";
	if (user != null) {
		username = user.getFname();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/css.css">
<script type="text/javascript">
function openwindow(url,name,iWidth,iHeight)
{
    var url;                             //转向网页的地址;
    var name;                            //网页名称，可为空;
    var iWidth;                          //弹出窗口的宽度;
    var iHeight;                         //弹出窗口的高度;
    //获得窗口的垂直位置
    var iTop = (window.screen.availHeight-30-iHeight)/2;       
    //获得窗口的水平位置
    var iLeft = (window.screen.availWidth-10-iWidth)/2;          
    window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
}
	function login(){
		openwindow ('login.jsp','login', 400,100);
	}
</script>
</head>
<body bgcolor="#01337a">
<table border="0" style="width: 100%;" cellpadding="0" cellspacing="0">
	<tr>
		<td width="13%"></td>
		<td colspan="2" class="adminTopLogo" style="margin-left: 100px;	font-size: 48px;color: yellow;font-weight: bolder;">360feedback</td>
		<td></td>
	</tr>
	<tr style="margin-right: 5%;">
		<td></td>
		<td></td>
		<%
			if(!"".equals(username)){
		%>
		<td class="adminTopUser">welcome,<%=username%>
		&nbsp;&nbsp;&nbsp; </td>
		<td width="13%"></td>
	
		<%
			}
		%>
	</tr>
</table>




</body>
</html>

