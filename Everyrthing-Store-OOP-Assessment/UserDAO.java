import java.sql.*;
import java.util.*;
// TAMANJI CHE CLAUD, MMUID 22460227
/**

The UserDAO class is responsible for adding, retrieval, deletion, and update of user information from the database.
*/
public class UserDAO {

  /**
* Constructs UserDAO object.
*/
    public UserDAO(){}

  /**
 * Connection to the database
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
 * Retrieves all users from the database.
 * 
 * @return an ArrayList of User objects 
 * @throws SQLException
 */
    public ArrayList<User> getAllUsers() throws SQLException {
        System.out.println("Retrieving all users ...");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM users;";
        ArrayList<User> users = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {

                int id = result.getInt("userID");
                String userName = result.getString("userName");
                String password = result.getString("userPassword");

                users.add(new User(id,userName,password));
            }
        } catch(Exception e) {
            System.out.println("get all users: "+e);
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
        return users;
    }

  /**
 * Retrieves a specific user from the database by their user ID.
 * 
 * @param id 
 * @return a User object 
 * @throws SQLException 
 */
    public User getUser(int id) throws SQLException {

        User user = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        String query = "SELECT * FROM users WHERE userID =" + id + ";";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery: " + query);
            // execute SQL query
            result = statement.executeQuery(query);

            while (result.next()) {


                int userId = result.getInt("userID");
                String userName = result.getString("userName");
                String password = result.getString("userPassword");

                user = new User(userId,userName,password);

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
        return user;
    }

  /**
* Deletes a specific user from the database by their user ID.
*
* @param id 
* @return true 
* @throws SQLException 
*/
    public Boolean deleteUser(int id) throws SQLException {
        System.out.println("Deleting user");
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;
        String query = "DELETE FROM users WHERE userID = " + id + ";";
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
* Updates a specific user in the database by their user ID.
*
* @param user the user object
* @return true 
* @throws SQLException 
*/
    public Boolean updateUser(User user) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE users SET userName = '"+ user.getUserName() +"', userPassword = '"+ PasswordHashing.hashPassword(user.getPassword()) +"' WHERE userID = "+ user.getUserId()+"; ";

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
* Adds a new user to the database.
*
* @param user the user object containing
* @return true 
* @throws SQLException 
*/
    public boolean addUser(User user) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String add = "INSERT INTO users (userName, userPassword) VALUES ('"+user.getUserName()+"','"+PasswordHashing.hashPassword(user.getPassword())+ "');";
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

  /**
* Login function, checks if the provided userName and password match a user in the database
*
* @param name
* @param pass
* @return true 
* @throws SQLException 
*/
    public User login(String name, String pass) throws SQLException {

        User user = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        String query = "SELECT * FROM users WHERE userName ='" + name + "' AND userPassword ='"+ PasswordHashing.hashPassword(pass) +"';";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery: " + query);
            // execute SQL query
            result = statement.executeQuery(query);

            while (result.next()) {


                int userId = result.getInt("userID");
                String userName = result.getString("userName");
                String password = result.getString("userPassword");

                user = new User(userId,userName,password);

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
        return user;
    }
}
