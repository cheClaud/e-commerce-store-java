import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessFindProductSearch implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("ProcessFindProduct Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        ProductDAO productDAO = new ProductDAO();

        System.out.println("about to get data");

        int search = Integer.parseInt(params.get("search"));

        System.out.println("about to find product"); // Debugging message
        ArrayList<String> cookies = Cookies.readCookies();


        try {
            Product product = productDAO.getProduct(search); // get data


            out.write(
                    "<html>" +
                            "<head> <title>Everything Store</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Product Result</h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>Title</th>" +
                            "    <th>Genre</th>" +
                            "    <th>Year</th>" +
                            "    <th>Rating</th>" +
                            "    <th>Update</th>" +
                            "    <th>Delete</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>"       +
                            "    <td>"+ product.getId() + "</td>" +
                            "    <td>"+ product.getSku() + "</td>" +
                            "    <td>"+ product.getDescription() + "</td>" +
                            "    <td>"+ product.getCategory() + "</td>" +
                            "    <td>&#163 "+ product.getPrice() + "</td>" +
                            "    <td> <a href=\"/products/update?id=" + product.getId() + "\"> update </a> </td>" +
                            "    <td> <a href=\"/products/delete?id=" + product.getId() + "\"> delete </a> </td>" +
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
