import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class DisplayProductLibrary implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        ProductDAO productDAO = new ProductDAO();
        ArrayList<String> cookies = Cookies.readCookies();
        try{
            ArrayList<Product> allProducts = productDAO.getAllProducts();

            out.write(
                    "<html>" +
                            "<head> <title>Everything Store</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h1> Products in Stock!</h1>"+
                            "<a href=\"/products/add\">Add Product</a><br>"+
                            "<a href=\"/products/find\">Find Product</a>"+
                            "<br>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>sku</th>" +
                            "    <th>Description</th>" +
                            "    <th>Category</th>" +
                            "    <th>Price</th>" +
                            "    <th>Update</th>" +
                            "    <th>Delete</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");

            for (Product product : allProducts){
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