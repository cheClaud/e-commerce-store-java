import com.sun.net.httpserver.*;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DisplayUserLibrary implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        UserDAO userDAO = new UserDAO();
        ArrayList<String> cookies = Cookies.readCookies();
        try{
            ArrayList<User> allUsers = userDAO.getAllUsers();

            out.write(
                    "<html>" +
                            "<head> <title>Everything Store</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h1> Users!</h1>"+
                            "<a href=\"/users/add\">Add User</a><br>"+
                            "<a href=\"/users/find\">Find User</a>"+
                            "<br>"+
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

            for (User user : allUsers){
                out.write(
                        "  <tr>"       +
                                "    <td>"+ user.getUserId() + "</td>" +
                                "    <td>"+ user.getUserName() + "</td>" +
                                "    <td>"+ user.getPassword() + "</td>" +
                                "    <td> <a href=\"/users/update?id=" + user.getUserId() + "\"> update </a> </td>" +
                                "    <td> <a href=\"/users/delete?id=" + user.getUserId() + "\"> delete </a> </td>" +
                                "  </tr>"
                );
            }
            out.write(
                    "</tbody>" +
                            "</table>" +
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
