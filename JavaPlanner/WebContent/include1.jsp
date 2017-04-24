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

	<div style="text-align: center;">Existing Tasks</div>
	<form id="azeqsd" name="azeqsd" method="post" action="loginServlet">
		<select name="tasks" size="2" onChange="loginServlet"
			style="width: 400px">
			<%
				for (int i = 0; i < tasks.size(); i++) {
			%>
			<option value="<%=i%>"><%=tasks.get(i).getDescription()%></option>
			<%
				}
			%>
		</select> <input type="submit" name="edit" value="Edit Task" /> <input
			type="submit" name="delete" value="Delete Task" />

	</form>
</body>
</html>
