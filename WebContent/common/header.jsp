<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
<!--
	function userProfile(){
		
	}

	function logout(){
		document.logoutForm.submit();
	}
//-->
</script>
<form action="logoutAction" method="post" name="logoutForm"></form>
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
						 <span 	class="icon-bar"></span> <span class="icon-bar"></span> </a> 
						<a	class="brand" href="#">360Feed Back</a>
						<div class="pull-right">
							<ul class="nav">
								<li><a class="dropdown-toggle" data-toggle="dropdown"
									href="#"> 
									Welcome! <i class="icon-user"></i> <%=session.getAttribute("user_name") %> </a>
									<ul class="dropdown-menu">
										<!--- <li><a href="#">Profile</a>
										</li>
										<li class="divider"></li> --->
										<li><a href="#" onclick="logout()">Sign Out</a></li>
									</ul></li>
							</ul>

						</div>
						<div class="nav-collapse">
							<!--         <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul> -->
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			<!--/.navbar navbar-fixed-top-->