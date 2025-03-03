// TAMANJI CHE CLAUD, MMUID 22460227
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class CustomerUpdateHandler implements HttpHandler{
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

        CustomerDAO customerDAO = new CustomerDAO();
        ArrayList<String> cookies = Cookies.readCookies();


        try {
            // get the customer's details before we delete from the Database
            Customer customer = customerDAO.getCustomer(id);

            out.write(
                    "<html>" +
                            "<head> <title>Everything Store</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h1> Update Customer</h1>" +
                            "<form method=\"get\" action=\"/customers/processUpdateCustomer\">" +
                            "<div class=\"form-group\"> " +
                            "<label for=\"id\">Customer ID</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\" value=\""+customer.getCustomerID()+"\"> " +

                            "<label for=\"firstName\">First Name</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"firstName\" id=\"firstName\" value=\""+customer.getFirstName()+"\"> " +

                            "<label for=\"lastName\">Last Name</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"lastName\" id=\"lastName\" value=\""+customer.getLastName()+"\"> " +

                            "<label for=\"house\">House Number</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"house\" id=\"house\" value=\""+customer.getAddress().getHouse()+"\"> " +

                            "<label for=\"addressLine1\">Address Line 1</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"addressLine1\" id=\"addressLine1\" value=\""+customer.getAddress().getAddressLine1()+"\">" +

                            "<label for=\"addressLine2\">Address Line 2</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"addressLine2\" id=\"addressLine2\" value=\""+customer.getAddress().getAddressLine2()+"\">" +

                            "<label for=\"country\">Country</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"country\" id=\"country\" value=\""+customer.getAddress().getCountry()+"\">" +

                            "<label for=\"postCode\">Postcode</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"postCode\" id=\"postCode\" value=\""+customer.getAddress().getPostCode()+"\">" +

                            "<label for=\"telephoneNumber\">Telephone</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"telephoneNumber\" id=\"telephoneNumber\" value=\""+customer.getTelephone()+"\">" +


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
