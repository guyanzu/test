<%@page import="com.dhtmlx.planner.api.DHXTimeSpan"%>
<%@page import="com.dhtmlx.planner.DHXMapType"%>
<%@page import="com.dhtmlx.planner.DHXSkin"%>
<%@page import="com.dhtmlx.planner.DHXPlanner"%>
<%@page import="java.util.*"%>
<%@page import="timetable.*"%>
<%@ page import="java.io.*"%>
<%@ page import="timetable.csp.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>Automated Scheduling and Planning</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./codebase/perso.css">
</head>
<body>


	<%
		ArrayList<Task> tasks = request.getAttribute("tasks") == null ? new ArrayList<Task>()
				: (ArrayList<Task>) request.getAttribute("tasks");
	%>

	<div style="text-align: center;">Add Task</div>
	<form name="unary" method="post" action="loginServlet">
		<div>
			name <input type="text" name="description" autocomplete="off">
		</div>
		<div>
			startHour <input type="text" name="startHour" autocomplete="off">
		</div>
		<div>
			duration <input type="text" name="duration" autocomplete="off">
		</div>
		<input type="submit" value="Add Task" />
	</form>

</body>
</html>
