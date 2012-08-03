<html>
<head>
<title>Logout</title>
<script>
	function submit() {
		document.logoutForm.submit();
	}
</script>
</head>

<body onload="submit();">
<form action="logoutAction" method="post" name="logoutForm"></form>
</body>
</html>