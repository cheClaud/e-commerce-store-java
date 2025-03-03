import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class FindCustomerSearch implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Add Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        ProductDAO productDAO = new ProductDAO();
        ArrayList<String> cookies = Cookies.readCookies();

        out.write(
                "<html>" +
                        "<head> <title>Everything Store</title> "+
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">"+
                        "<h1> Find Customer</h1>"+
                        "<form method=\"get\" action=\"/customers/processFindPCustomer\">" +
                        "<div class=\"form-group\"> "+
                        "<label for=\"search\">Enter Customer ID</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"search\" id=\"search\"> " +

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
