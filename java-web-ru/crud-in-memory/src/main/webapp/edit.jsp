<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit user</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <a href="/users">Все пользователи</a>
            <!-- BEGIN -->
            <div>${error}</div>
            <form action="/users/edit?id=${user.get("id")}" method="post">
                <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">firstName</label>
                    <input class="form-control" id="exampleFormControlTextarea1" type="firstName" name="firstName" value="${user.getOrDefault("firstName", "")}">
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlTextarea2" class="form-label">lastName</label>
                    <input class="form-control" id="exampleFormControlTextarea2" type="lastName" name="lastName" value="${user.getOrDefault("lastName", "")}">
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${user.getOrDefault("email", "")}">
                </div>
                <button type="submit" class="btn btn-primary" value="OK">Submit</button>
            </form>
            <!-- END -->
        </div>
    </body>
</html>
