package exercise.servlet;

import com.github.javafaker.Company;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        String param = request.getParameter("search");
        if(param == null || param.equals("")) {
            for(String item : getCompanies()) {
                out.println(item);
            }
        }
        boolean flag = false;
        if(param != null && !param.equals("")) {
            for(String item : getCompanies()) {
                if(item.contains(param)){
                    out.println(item);
                    flag = true;
                }
            }
            if(flag == false) {
                out.println("Companies not found");
            }
        }

        // END
    }
}
