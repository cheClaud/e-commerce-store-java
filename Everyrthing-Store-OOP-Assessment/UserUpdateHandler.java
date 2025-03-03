import com.sun.net.httpserver.*;

import java.io.*;
import java.sql.*;
import java.util.*;

public class UserUpdateHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Update Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        int id = Integer.parseInt(params.get("id"));

        UserDAO userDAO = new UserDAO();
        ArrayList<String> cookies = Cookies.readCookies();


        try {
            // get the user's details before we delete from the Database
            User user = userDAO.getUser(id);

            out.write(
                    "<html>" +
                            "<head> <title>Everything Store</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h1> Update User</h1>" +
                            "<form method=\"get\" action=\"/users/processUpdateUsers\">" +
                            "<div class=\"form-group\"> " +
                            "<label for=\"id\">User ID</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\" value=\""+user.getUserId()+"\"> " +

                            "<label for=\"userName\">Username</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"userName\" id=\"userName\" value=\""+user.getUserName()+"\"> " +

                            "<label for=\"password\">Password</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"password\" id=\"password\" > " +

                            "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
                            "</div>" +
                            "</form>" +
                            "<a href=\"/login/processLogin?userName="+cookies.get(0)+"&password="+cookies.get(1)+"\">Back to Menu </a>"+
                            "</div>" +
                            "</body>" +
                            "</html>");

        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        out.close();
    }
}
