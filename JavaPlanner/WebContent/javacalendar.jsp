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

		<div id="left" style="float: left; width: 33%;">
			<jsp:include page="/include1.jsp" />
			<hr />
			<jsp:include page="/include2.jsp" />
			<hr />
			<jsp:include page="/include3.jsp" />
		</div>

		<div id="right" style="float: right; width: 67%;">
			<jsp:include page="/include4.jsp" />
		</div>

</body>
</html>
