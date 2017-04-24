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

	<%
		if (request.getAttribute("target") != null) {
			int id = Integer.parseInt((String) request.getAttribute("target"));
	%>

	<div style="text-align: center;">
		Edit Task
		<%=tasks.get(id).getDescription()%> 
		<br>
	</div>

	<div>Time Frames</div>

	<div>
		<form id="qsdfgh" name="qsdfgh" method="post" action="loginServlet">
			<select name=ucToDelete size="2" style="width: 400px">

				<%
					ArrayList<UnaryConstraint> ucList = tasks.get(id).getUnaryConstraints();
						for (int i = 0; i < ucList.size(); i++) {
							UnaryConstraint uc = ucList.get(i);
				%>
				<option value=<%=id + ";" + i%>><%=uc.getStart() + " " + uc.getEnd()%></option>
				<%
					}
				%>
			</select> <input type="submit" name="deleteUC" value="Delete Time Frame" />
		</form>
	</div>

	<div>
		<form name="unary" method="post" action="loginServlet">
			<input type="hidden" name="modifiedTaskUC"
				value="<%=request.getAttribute("target")%>"> startHour <input
				type="text" name="timeframeA" autocomplete="off"> endHour <input
				type="text" name="timeframeB" autocomplete="off"> <input
				type="submit" value="Add Time Frame" />
		</form>
	</div>

	<div>
		Constraints

		<form id="qsdfgh" name="qsdfgh" method="post" action="loginServlet">
			<select name=bcToDelete size="2" style="width: 400px">


				<%
					ArrayList<BinaryConstraint> bcList = tasks.get(id).getBinaryConstraints();
						for (int i = 0; i < bcList.size(); i++) {
							BinaryConstraint bc = bcList.get(i);
				%>
				<option value="<%=id + ";" + i%>"><%=bc.getComparator() + " " + bc.getTarget() + " " + bc.getDuration() + " " + bc.getUnit()
							+ " " + bc.getModifier()%></option>

				<%
					}
				%>
			</select> <input type="submit" name="deleteBC" value="Delete Constraint" />
		</form>
	</div>

	<div>
		<form name="binary" method="post" action="loginServlet">
			<input type="hidden" name="modifiedTaskBC"
				value="<%=request.getAttribute("target")%>"> <select
				name="comparator">
				<option value="<">before</option>
				<option value=">">after</option>
				<option value="<=">not after</option>
				<option value=">=">not before</option>
				<option value="dist">distant of</option>
			</select> <select name=targetC>
				<%
					if (tasks.size() > 0) {
							for (int i = 0; i < tasks.size(); i++) {
				%>
				<option value="<%=tasks.get(i).getDescription()%>"><%=tasks.get(i).getDescription()%></option>
				<%
					}
						}
				%>
			</select> <input type="text" name="duration" autocomplete="off"> <select
				name=unit>
				<option value="hour">hour</option>
			</SELECT> <select name="modifier">
				<option value="null"></option>
				<option value="min">min</option>
				<option value="max">max</option>
				<option value="exactly">exactly</option>
			</select> <input type="submit" value="Add Constraint" />

		</form>

		<%
			}
		%>
	</div>
</body>
</html>
