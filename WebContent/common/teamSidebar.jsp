<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="sidebar">
<ul class="sidemenu" id="sidemenu">
	<li><a href="<%=basePath %>admin/survey/survey_home.jsp">Survey</a></li>
	<li><a	 href="<%=basePath %>admin/setting/survey_setting_home.jsp">Setting</a></li>
	<li class="active"><a href="<%=basePath %>admin/team/team.jsp">Team</a></li>
	<li><a href="<%=basePath %>admin/report/survey_report_home.jsp">Report</a></li>
</ul>
</div>