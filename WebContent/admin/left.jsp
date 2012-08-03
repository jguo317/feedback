<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title>Untitled Document</title>
        <style type="text/css">
        	 a:link {
                color: black;
                text-decoration: none
            }
           a:visited {
                color: red;
                text-decoration: none;
				font-size:larger;
            }
            
             a:hover {
                color: green;
                text-decoration: none;
            }
			
			a:acitve{
				color:#FFFF00;
			}
			
			ul li{
				margin-bottom:5px;
			}
            
        </style>
    </head>
    <body bgcolor="#E4E5E6">
        <ul>
        	<li>
                <a href="survey/survey_home.jsp" target="rightFrame">Survey</a>
            </li>
             <li>
                <a href="setting/survey_setting_home.jsp" target="rightFrame">Setting</a>
            </li>            
            <li>
                <a href="team/team.jsp" target="rightFrame">Team</a>
            </li>
            <li>
                <a href="report/survey_report_home.jsp" target="rightFrame">Report</a>
            </li>  
            <li>
                <a href="logout.jsp" target="rightFrame">Logout</a>
            </li>           
        </ul>
    </body>
</html>
