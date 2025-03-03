import com.sun.net.httpserver.*;

import java.io.*;
import java.sql.*;
import java.util.*;

public class UserAddHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Add Handler is Called now");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        ArrayList<String> cookies = Cookies.readCookies();

        out.write(
                "<html>" +
                        "<head> <title>Everything Store</title> "+
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">"+
                        "<h1> Add User</h1>"+
                        "<form method=\"get\" action=\"/users/processAddUsers\">" +
                        "<div class=\"form-group\"> "+
                        "<label for=\"userName\">Username</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"userName\" id=\"userName\" > " +

                        "<label for=\"password\">Password</label> " +
                        "<input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\" > " +

                        "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
                        "</div>" +
                        "</form>" +
                        "<a href=\"/login/processLogin?userName="+cookies.get(0)+"&password="+cookies.get(1)+"\">Back to Menu </a>"+
                        "</div>" +
                        "</body>" +
                        "</html>");

        out.close();
    }
}
