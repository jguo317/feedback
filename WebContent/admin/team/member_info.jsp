<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/css.jsp"%>
<script type="text/javascript">
function addEmail(){
	var fname=document.getElementById("fname").value;
	var mname=document.getElementById("mname").value;
	var lname=document.getElementById("lname").value;
	var email=document.getElementById("email");	
	if(fname!=""&&fname.length>0){
		if(lname!=""&&lname.length>0){
			if(mname!=""&&mname.length>0){
				email.value=fname+"."+mname+"."+lname+"@activenetwork.com";
			}else{
				email.value=fname+"."+lname+"@activenetwork.com";
			}
		}else{
			alert("please input last name");
		}
	}else{
		alert("please input first name");
	}
}

</script>
</head>
<body>
<div id="content">
<div class="container"><%@ include file="/common/header.jsp"%>
<div class="block withsidebar">
<div class="block_head">
<div class="bheadl"></div>
<div class="bheadr"></div>
<h2>Survey</h2>

</div>
<div class="block_content"><%@ include
	file="/common/teamSidebar.jsp"%>
<div class="sidebar_content">
<form action="memberInfoCEAction"><input type="hidden"
	name="action" id="action" value="<s:property value='action'/>" /> <input
	type="hidden" name="teamId" value="<s:property value='teamId' />" />
<fieldset><legend
	style="font-weight: bold; margin-left: 10px;">Member Info</legend>
<table>
	<tr>
		<td>Member First Name:</td>
		<td><input type="text" name="fname" id="fname"
			value="<s:property value='member.fname'/>" size="30"></td>
	</tr>
	<tr>
		<td>Member Middle Name:</td>
		<td><input type="text" name="mname" id="mname"
			value="<s:property value='member.mname'/>" size="30"></td>
	</tr>
	<tr>
		<td>Member Last Name:</td>
		<td><input type="text" name="lname" id="lname"
			value="<s:property value='member.lname'/>" size="30" onblur="addEmail();"></td>
	</tr>
	<tr>
		<td>Member Email:</td>
		<td><input type="text" name="email" id="email"
			value="<s:property value='member.email'/>" size="30" readonly></td>
	</tr>
	<tr>
		<s:if test="%{action == 'add'}">
			<td colspan=2 align="center"><input type="submit" value="Create"></td>
		</s:if>
		<s:else>
			<td><input type="submit" value="Update"></td>
		</s:else>
	</tr>

</table>
</fieldset>
</form>
</div>

</div>
<div class="bendl"></div>
<div class="bendr"></div>

</div>
<!--/block withsidebar--> <%@ include file="/common/footer.jsp"%>
</div>
<!--/.container --></div>
</body>
</html>