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
    <%Map<String, String> user = (Map<String, String>) request.getAttribute("user");%>
    <tr>
        <td><%= user.get("id") %></td>
        <td>
            <%= user.get("firstName") + " " + user.get("lastName")%>
        </td>
        <td><%= user.get("email") %></td>
    </tr>
    <tr>
        <td>
            <a href="delete?id=<%=user.get("id")%>">Delete</a>
        </td>
    </tr>
</table>
</body>
</html>
<!-- END -->
