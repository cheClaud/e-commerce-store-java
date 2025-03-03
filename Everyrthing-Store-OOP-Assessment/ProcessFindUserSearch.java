import com.sun.net.httpserver.*;

import java.io.*;
import java.sql.*;
import java.util.*;

public class ProcessFindUserSearch implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("ProcessFindCustomer Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        UserDAO userDAO = new UserDAO();

        System.out.println("about to get data");

        int search = Integer.parseInt(params.get("search"));

        System.out.println("about to find user"); // Debugging message
        ArrayList<String> cookies = Cookies.readCookies();


        try {
            User user = userDAO.getUser(search); // get data


            out.write(
                    "<html>" +
                            "<head> <title> Everything Store</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> User Result</h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>Username</th>" +
                            "    <th>Password</th>" +
                            "    <th>update</th>" +
                            "    <th>Delete</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>"       +
                            "    <td>"+ user.getUserId() + "</td>" +
                            "    <td>"+ user.getUserName() + "</td>" +
                            "    <td>"+ user.getPassword() + "</td>" +
                            "    <td> <a href=\"/users/update?id=" + user.getUserId() + "\"> update </a> </td>" +
                            "    <td> <a href=\"/users/delete?id=" + user.getUserId() + "\"> delete </a> </td>" +
                            "  </tr>"
            );

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/login/processLogin?userName="+cookies.get(0)+"&password="+cookies.get(1)+"\">Back to Menu </a>"+
                            "</body>" +
                            "</html>");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }

        out.close();
    }
}
