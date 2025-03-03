// TAMANJI CHE CLAUD, MMUID 22460227
/**
 *
 * The User class represents a user in the system with a unique userId, userName, and password.
 */
public class User {

  /**
     * The unique userId of the user.
     */
    private int userId;

  /**
     * The userName of the user.
     */
    private String userName;

  /**
     * The password of the user.
     */
    private String password;

  /**
     * Constructs a new User object.
     *
     * @param userId
     * @param userName
     * @param password
     */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

  /**
 * Returns the userId.
 * 
 * @return userId
 */
    public int getUserId() {
        return userId;
    }

  /**
 * Sets the userId.
 * 
 * @param userId
 */
    public void setUserId(int userId) {
        this.userId = userId;
    }

  /**
 * Returns the userName.
 * 
 * @return userName
 */
    public String getUserName() {
        return userName;
    }

  /**
 * Sets the userName.
 * 
 * @param userName
 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

  /**
 * Returns the password.
 * 
 * @return password
 */
    public String getPassword() {
        return password;
    }

  /**
 * Sets the password.
 * 
 * @param password
 */
    public void setPassword(String password) {
        this.password = password;
    }

  /**
 * Returns User object 
 *
 * @return a string representation of the User object
 */
    @Override
    public String toString() {
        return " | " + this.userId + " | " + this.userName + " | " + this.password + " | ";
    }
}
