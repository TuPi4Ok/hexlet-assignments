package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/main/resources/users.json"), ArrayList.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map> users = getUsers();
            users.stream()
                    .sorted(Comparator.comparing(x -> Integer.valueOf((String)x.get("id"))))
                    .toList();
            StringBuilder body = new StringBuilder();
            body.append("""
                    <!DOCTYPE html>
                    <html lang=\"ru\">
                        <head>
                            <meta charset=\"UTF-8\">
                            <title>Example application | Users</title>
                        </head>
                        <body>
                        <table style=\"border: 1px solid #333\">
                        <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>FullName</th>
                                </tr>
                        </thead>
                    """);

            for (Map user : users) {

                body.append(" <tbody>\n" +
                        "        <tr style=\"border: 1px solid #333\">\n" +
                        "            <td style=\"border: 1px solid #333\">" + user.get("id") + "</td>\n" +
                        "            <td style=\"border: 1px solid #333\"><a href='/users/" + user.get("id") + "'>" + user.get("firstName") + " " + user.get("lastName") + "</a></td>\n" +
                        "        </tr>\n" +
                        "    </tbody>");
            }

            body.append("""
                        </table>
                    </body>
                </html>
                """);

            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();
            out.println(body.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map> users = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("""
                    <!DOCTYPE html>
                    <html lang=\"ru\">
                        <head>
                            <meta charset=\"UTF-8\">
                            <title>Example application | Users</title>
                        </head>
                        <body>
                        <table style=\"border: 1px solid #333\">
                        <thead>
                                <tr>
                                    <th>Key</th>
                                    <th>Value</th>
                                </tr>
                        </thead>
                    """);
        boolean flag = false;
        for (Map user : users) {
            if(user.get("id").equals(id.toString())) {
                body.append(" <tbody>\n" +
                        "        <tr style=\"border: 1px solid #333\">\n" +
                        "            <td style=\"border: 1px solid #333\">Id</td>\n" +
                        "            <td style=\"border: 1px solid #333\"> " + user.get("id") + " </td>\n" +
                        "        </tr>\n");
                body.append(
                        "        <tr style=\"border: 1px solid #333\">\n" +
                        "            <td style=\"border: 1px solid #333\">firstName</td>\n" +
                        "            <td style=\"border: 1px solid #333\"> " + user.get("firstName") + " </td>\n" +
                        "        </tr>\n");
                body.append(
                        "        <tr style=\"border: 1px solid #333\">\n" +
                                "            <td style=\"border: 1px solid #333\">lastName</td>\n" +
                                "            <td style=\"border: 1px solid #333\"> " + user.get("lastName") + " </td>\n" +
                                "        </tr>\n");
                body.append(
                        "        <tr style=\"border: 1px solid #333\">\n" +
                                "            <td style=\"border: 1px solid #333\">email</td>\n" +
                                "            <td style=\"border: 1px solid #333\"> " + user.get("email") + " </td>\n" +
                                "        </tr>\n" +
                                "</tbody>");
                flag = true;
            }

        }
        if(!flag) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        body.append("""
                        </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }
}
