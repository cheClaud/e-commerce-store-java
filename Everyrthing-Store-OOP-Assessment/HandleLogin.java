import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.util.*;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class HandleLogin implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody() ));

        UserDAO userDAO = new UserDAO();

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        String userName = params.get("userName");
        String password = params.get("password");

        try {
            Cookies.cookies(userName, password);
            userDAO.login(userName, password);
//            User user = userDAO.login(userName, password);

            out.write(
                    "<html>" +
                            "<head> <title>Everything Store</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h4>Everything Store</h4>"+
                            "<br>"+
                            "<a href=\"/products\">Products </a> " +
                            "<br>" +
                            "<a href=\"/customers\">Customers </a> " +
                            "<br>" +
                            "<a href=\"/users\">Users </a> " +
                            "</div>" +
                            "</body>" +
                            "</html>");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }

        out.close();

    }

}