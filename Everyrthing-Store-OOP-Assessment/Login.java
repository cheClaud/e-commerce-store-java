import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class Login implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Login Handler is called now");
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        UserDAO userDAO = new UserDAO();


       out.write(
                    "<html>" +
                            "<head> <title>Everything Store - Login</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h1> Login</h1>" +
                            "<form method=\"get\" action=\"/login/processLogin\">" +
                            "<div class=\"form-group\"> " +
                            "<label for=\"userName\">Username</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"userName\" id=\"userName\" value=\"admin\"> " +

                            "<label for=\"password\">Password</label> " +
                            "<input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\" value=\"admin\"> " +

                            "<button type=\"submit\" class=\"btn btn-primary\">Login</button> " +
                            "</div>" +
                            "</form>" +
//                        "<a href=\"/\">Back to List </a>"+
                            "</div>" +
                            "</body>" +
                            "</html>");

        out.close();
    }
}
