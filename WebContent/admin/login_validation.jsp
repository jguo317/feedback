	<script>
		var userId = "<%=session.getAttribute("user_id") %>".toString();
		if (userId == 'null') self.location.href="login.jsp";
	</script>
