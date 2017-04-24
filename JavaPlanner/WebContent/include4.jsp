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

		String nameOfTextFile = "c:/users/julien/desktop/data.txt";
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(nameOfTextFile));
			for (Task task : tasks) {
				String temp = task.getDescription() + ";" + task.getStartDate().getHours() + ";"
						+ task.getDuration();
				pw.println(temp);
			}
			pw.close();
		} catch (IOException e) {
			out.println(e.getMessage());
		}
	%>

	<div class="planner" id="planner"><%=getPlanner(request)%>
		<%@ page import="com.dhtmlx.planner.data.*"%>
		<%!String getPlanner(HttpServletRequest request) throws Exception {
		DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
		s.setWidth(900);
		s.setInitialDate(2016, 12, 14);
		s.load("events.jsp", DHXDataFormat.JSON);
		return s.render();
	}%>
	</div>

</body>
</html>
