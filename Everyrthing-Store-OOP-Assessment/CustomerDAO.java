// TAMANJI CHE CLAUD, MMUID 22460227
import java.sql.*;
import java.util.*;

/**
 *
 * The CustomerDAO class provides methods for interacting with a database of customers.
 * It uses the JDBC API to connect to the database and execute SQL commands.
 */
public class CustomerDAO {
      /**
     * Constructor for the CustomerDAO class.
     */
    public CustomerDAO(){}

      /**
     *
     * @return 
     */
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:database.sqlite";
            dbConnection = DriverManager.getConnection(dbURL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

      /**
     *
     * @return 
     * @throws SQLException 
     */
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        System.out.println("Retrieving all Customers ...");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM customers;";
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {

                int customerID = result.getInt("customerID");
                String firstName = result.getString("firstName");
                String lastName = result.getString("secondName");
                String house = result.getString("house");
                String addressLine1 = result.getString("addressLine1");
                String addressLine2 = result.getString("addressLine2");
                String country = result.getString("country");
                String postCode = result.getString("postcode");
                String telephone = result.getString("telephoneNumber");

                customers.add(new Customer(customerID,firstName,lastName, new Address(house, addressLine1, addressLine2, country, postCode), telephone));
            }
        } catch(Exception e) {
            System.out.println("get all customers: "+e);
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return customers;
    }

      /**
     *
     * @param id 
     * @return 
     * @throws SQLException 
     */
    public Customer getCustomer(int id) throws SQLException {

        Customer customer = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        String query = "SELECT * FROM customers WHERE customerID =" + id + ";";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery: " + query);
            // execute SQL query
            result = statement.executeQuery(query);

            while (result.next()) {


                int customerID = result.getInt("customerID");
                String firstName = result.getString("firstName");
                String lastName = result.getString("secondName");
                String house = result.getString("house");
                String addressLine1 = result.getString("addressLine1");
                String addressLine2 = result.getString("addressLine2");
                String country = result.getString("country");
                String postCode = result.getString("postcode");
                String telephone = result.getString("telephoneNumber");

                customer = new Customer(customerID,firstName,lastName, new Address(house, addressLine1, addressLine2, country, postCode), telephone);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return customer;
    }

      /**
     *
     * @param id 
     * @return 
     * @throws SQLException 
     */
    public Boolean deleteCustomer(int id) throws SQLException {
        System.out.println("Deleting Customer");
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;
        String query = "DELETE FROM customers WHERE customerID = " + id + ";";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(query);
            // execute SQL query
            result = statement.executeUpdate(query);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

      /**
     *
     * @param customer 
     * @return 
     * @throws SQLException 
     */
    public Boolean updateCustomer(Customer customer) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE customers SET firstName = '"+ customer.getFirstName() +"', secondName = '"+ customer.getLastName() +"', house = '"+ customer.getAddress().getHouse() +"', addressLine1 = '"+ customer.getAddress().getAddressLine1() +"', addressLine2 = '"+ customer.getAddress().getAddressLine2() +"', country = '"+ customer.getAddress().getCountry() +"', postcode = '"+ customer.getAddress().getPostCode() +"', telephoneNumber = '"+ customer.getTelephone() +"' WHERE customerID = "+ customer.getCustomerID()+"; ";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(query);
            // execute SQL update
            statement.executeUpdate(query);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;

        } finally {

            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return true;
    }

      /**
     *
     * @param customer 
     * @return true 
     * @throws SQLException 
     */
    public boolean addCustomer(Customer customer) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String add = "INSERT INTO customers (firstName, secondName, house, addressLine1, addressLine2, country, postcode, telephoneNumber) VALUES ('"+customer.getFirstName()+"','"+customer.getLastName()+ "','"+customer.getAddress().getHouse()+ "', '"+customer.getAddress().getAddressLine1()+ "', '"+customer.getAddress().getAddressLine2()+ "', '"+customer.getAddress().getCountry()+ "', '"+customer.getAddress().getPostCode()+ "', '"+customer.getTelephone()+ "');";
        boolean ok = false;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(add);
            // execute SQL query
            statement.execute(add);
            ok = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return ok;
    }
}
