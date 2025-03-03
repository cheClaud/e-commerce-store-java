// TAMANJI CHE CLAUD, MMUID 22460227
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class DisplayCustomerLibrary implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        CustomerDAO customerDAO = new CustomerDAO();
        ArrayList<String> cookies = Cookies.readCookies();
        try{
            ArrayList<Customer> allCustomers = customerDAO.getAllCustomers();

            out.write(
                    "<html>" +
                            "<head> <title>Everything Store</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">" +
                            "<h1> Customer!</h1>"+
                            "<a href=\"/customers/add\">Add Customer</a><br>"+
                            "<a href=\"/customers/find\">Find Customer</a>"+
                            "<br>"+
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

            for (Customer customer : allCustomers){
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