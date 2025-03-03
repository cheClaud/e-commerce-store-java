// TAMANJI CHE CLAUD, MMUID 22460227
/**
 *
 * The Customer class represents a customer and contains their personal information such as their name, address, and telephone number.
 */
public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private Address address;
    private String telephone;

  /**
     * Constructor for the Customer class.
     *
     * @param customerID   
     * @param firstName    
     * @param lastName    
     * @param address      
     * @param telephone    
     */
    public Customer(int customerID, String firstName, String lastName, Address address, String telephone) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

      /**
     *
     * @return  the customer's ID
     */
    public int getCustomerID() {
        return customerID;
    }

      /**
     *
     * @param customerID   
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

      /**
     *
     * @return  the customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

  /**
     *
     * @param firstName   
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

      /**
     *
     * @return  the customer's last name
     */
    public String getLastName() {
        return lastName;
    }

      /**
     *
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

      /**
     *
     * @return  the customer's address
     */
    public Address getAddress() {
        return address;
    }

      /**
     *
     * @param address   
     */
    public void setAddress(Address address) {
        this.address = address;
    }

      /**
     *
     * @return  the customer's telephone number
     */
    public String getTelephone() {
        return telephone;
    }

      /**
     *
     * @param telephone the customer's telephone number
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return " | " + this.getCustomerID() + " | " + this.getFirstName() + " | " + this.getLastName() + " | " + this.getAddress() + " | " + this.getTelephone();
    }
}
