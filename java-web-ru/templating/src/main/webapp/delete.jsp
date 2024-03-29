<%@ page import="java.util.Map" %>
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
    <%Map<String,String> user = (Map<String, String>) request.getAttribute("user");%>
    <form action="delete?id=<%=user.get("id")%>" method="post">
        <h3>Are you sure you want to delete <%=user.get("firstName")%>?</h3>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</body>
</html>
<!-- END -->
