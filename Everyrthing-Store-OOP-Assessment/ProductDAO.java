import java.sql.*;
import java.util.*;
// TAMANJI CHE CLAUD, MMUID 22460227
/**
 *
 * The ProductDAO class represents a Data Access Object for products.
 * It provides methods for performing CRUD operations on products.
 */
public class ProductDAO {

      /**
     * Constructs a new ProductDAO object.
     */
    public ProductDAO() {}

      /**
     *
     * @return Connection 
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
     * Retrieves all products from the database and returns them in an ArrayList.
     *
     * @return ArrayList 
     * @throws SQLException 
     */
    public ArrayList<Product> getAllProducts() throws SQLException {
        System.out.println("Retrieving all Products ...");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM products;";
        ArrayList<Product> products = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {

                int id = result.getInt("id");
                String sku = result.getString("sku");
                String description = result.getString("description");
                String category = result.getString("category");
                int price = result.getInt("price");
                products.add(new Product(id,sku,description,category,price));
            }
        } catch(Exception e) {
            System.out.println("get all products: "+e);
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
        return products;
    }

      /**
     * Retrieves a product from the database with the given id and return Product object.
     *
     * @param id 
     * @return Product 
     * @throws SQLException 
     */
    public Product getProduct(int id) throws SQLException {

        Product product = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        String query = "SELECT * FROM products WHERE id =" + id + ";";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery: " + query);
            // execute SQL query
            result = statement.executeQuery(query);

            while (result.next()) {


                int prod_id = result.getInt("id");
                String sku = result.getString("sku");
                String description = result.getString("description");
                String category = result.getString("category");
                int price = result.getInt("price");
                product = new Product(prod_id,sku,description,category,price);

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
        return product;
    }

       /** Deletes a single product from the database with the given id.
     *
     * @param id t
     * @return true 
     * @throws SQLException
     */
    public Boolean deleteProduct(int id) throws SQLException {
        System.out.println("Deleting product");
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;
        String query = "DELETE FROM products WHERE id = " + id + ";";
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

      /** Updates a single product in the database with the given id,sku,description,category and price.
     *
     * @param product
     * @return true
     * @throws SQLException 
     */
    public Boolean updateProduct(Product product) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE products SET sku = '"+ product.getSku() +"', description = '"+ product.getDescription()+"', category = '"+ product.getCategory()+"', price = "+ product.getPrice()+" WHERE id = "+ product.getId()+"; ";

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

      /** Adds a new product to the database with the given sku,description,category and price.
     *
     * @param product
     * @return true 
     * @throws SQLException 
     */
    public boolean addProduct(Product product) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String add = "INSERT INTO products (sku, description, category, price) VALUES ('"+product.getSku()+"','"+product.getDescription()+"','"+product.getCategory()+"',"+product.getPrice()+ ");";
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
