import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class UpdateHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Update Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        int id = Integer.parseInt(params.get("id"));

        ProductDAO productDAO = new ProductDAO();
        ArrayList<String> cookies = Cookies.readCookies();


        try {
            // get the product details before we delete from the Database
            Product product = productDAO.getProduct(id);

            out.write(
                    "<html>" +
                            "<head> <title>Everything Store</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h1> Update Product</h1>" +
                            "<form method=\"get\" action=\"/products/processUpdateProduct\">" +
                            "<div class=\"form-group\"> " +
                            "<label for=\"id\">ID</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\" value=\""+product.getId()+"\"> " +

                            "<label for=\"sku\">SKU</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\" value=\""+product.getSku()+"\"> " +

                            "<label for=\"description\">Description</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\" value=\""+product.getDescription()+"\"> " +

                            "<label for=\"category\">Category</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"category\" id=\"category\" value=\""+product.getCategory()+"\"> " +

                            "<label for=\"price\">Price</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\" value=\""+product.getPrice()+"\">" +
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
