import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessFindCustomerSearch implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("ProcessFindCustomer Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        CustomerDAO customerDAO = new CustomerDAO();

        System.out.println("about to get data");

        int search = Integer.parseInt(params.get("search"));

        System.out.println("about to find customer"); // Debugging message
        ArrayList<String> cookies = Cookies.readCookies();


        try {
            Customer customer = customerDAO.getCustomer(search); // get data


            out.write(
                    "<html>" +
                            "<head> <title> Everything Store</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Customer Result</h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>First Name</th>" +
                            "    <th>LastName</th>" +
                            "    <th>House</th>" +
                            "    <th>AddressLine 1</th>" +
                            "    <th>AddressLine 2</th>" +
                            "    <th>Country</th>" +
                            "    <th>Postcode</th>" +
                            "    <th>Telephone</th>" +
                            "    <th>update</th>" +
                            "    <th>Delete</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>"       +
                            "    <td>"+ customer.getCustomerID() + "</td>" +
                            "    <td>"+ customer.getFirstName() + "</td>" +
                            "    <td>"+ customer.getLastName() + "</td>" +
                            "    <td>"+ customer.getAddress().getHouse() + "</td>" +
                            "    <td>"+ customer.getAddress().getAddressLine1() + "</td>" +
                            "    <td>"+ customer.getAddress().getAddressLine2() + "</td>" +
                            "    <td>"+ customer.getAddress().getCountry() + "</td>" +
                            "    <td>"+ customer.getAddress().getPostCode() + "</td>" +
                            "    <td>"+ customer.getTelephone() + "</td>" +
                            "    <td> <a href=\"/customers/update?id=" + customer.getCustomerID() + "\"> update </a> </td>" +
                            "    <td> <a href=\"/customers/delete?id=" + customer.getCustomerID() + "\"> delete </a> </td>" +
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
