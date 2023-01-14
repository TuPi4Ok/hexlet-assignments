<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="exercise.servlet.UsersServlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Company</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<table>
<% List<Map<String, String>> users = (List<Map<String, String>>) request.getAttribute("users");
    for(Map<String,String> item : users){%>
    <tr>
        <td><%= item.get("id") %></td>
        <td>
            <a href="users/show?id=<%=item.get("id")%>"><%= item.get("firstName") + " " + item.get("lastName")%></a></td>
        <td><%= item.get("email") %></td>
    </tr>
    <%}%>
</table>
</body>
</html>
<!-- END -->
