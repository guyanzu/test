<%@page contentType="application/json"%>
<%=getEvents(request) %>
<%@ page import="com.dhtmlx.planner.*,timetable.*" %>

<%!
String getEvents(HttpServletRequest request) throws Exception{
		EventsManager evs  = new EventsManager(request);
		return evs.run();
}

%>
