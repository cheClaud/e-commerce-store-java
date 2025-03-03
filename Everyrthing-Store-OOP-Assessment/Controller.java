// TAMANJI CHE CLAUD, MMUID 22460227
import com.sun.net.httpserver.*;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;

/**

The Controller class allows for the user to access different menus for managing products, customers, and the web server.
*/
public class Controller {

  /**
* Runs the product management menu.
* @throws SQLException 
* @throws IOException 
*/
    public static void runProducts() throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        String selection;
        ProductDAO productDAO = new ProductDAO();
        do {
            System.out.println("_________________________");
            System.out.println("The Everything Store");
            System.out.println("Choose from these options");
            System.out.println("___________________________");

            System.out.println("[1] List all Products");
            System.out.println("[2] List a Product");
            System.out.println("[3] Add a Product");
            System.out.println("[4] Update a Product");
            System.out.println("[5] Delete a Product");
            System.out.println("[00] Go back to the Main Menu");
            System.out.println("[99] Exit\n");

            selection = input.nextLine();

            switch (selection) {
                case "1":
                    System.out.println(productDAO.getAllProducts());
                    break;
                case "2":
                    System.out.println("Enter ID of the Product you wish to find: ");
                    int prod_id = input.nextInt();
                    System.out.println(productDAO.getProduct(prod_id));
                    break;
                case "3":
                    System.out.println("Enter sku: ");
                    String sku = input.nextLine();

                    System.out.println("Enter Description: ");
                    String description = input.nextLine();

                    System.out.println("Enter Category: ");
                    String category = input.nextLine();

                    System.out.println("Enter Price: ");
                    int price = input.nextInt();
                    input.nextLine();

                    Product product = new Product(0, sku, description, category, price);
                    productDAO.addProduct(product);
                    System.out.println("Product Added");
                    break;
                case "4":

                    System.out.println("Enter the ID of the Product you wish to update");
                    int upDateId = input.nextInt();
                    input.nextLine();

                    System.out.println("Enter New sku: ");
                    String newSku = input.nextLine();

                    System.out.println("Enter New Description: ");
                    String newDescription = input.nextLine();

                    System.out.println("Enter New Category: ");
                    String newCategory = input.nextLine();

                    System.out.println("Enter New Price: ");
                    int newPrice = input.nextInt();
                    input.nextLine();

                    Product newProduct = new Product(upDateId, newSku, newDescription, newCategory, newPrice);
                    productDAO.updateProduct(newProduct);
                    System.out.println("Product Updated");
                    break;
                case "5":
                    System.out.println("Enter the Product ID you wish to delete");
                    int del_Id = input.nextInt();
                    productDAO.deleteProduct(del_Id);
                    System.out.println("Product Deleted");
                    break;
                case "00":
                    Controller.menu();
                    break;
                default:
            }
        } while (!selection.equals("99"));

        input.close();
    }

  /**
* Runs the customer management menu.
* @throws SQLException 
* @throws IOException
*/
    public static void runCustomer() throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        String selection;
        CustomerDAO customerDAO = new CustomerDAO();
        do {
            System.out.println("_______________________");
            System.out.println("The Everything Store");
            System.out.println("Choose from these options");
            System.out.println("_________________________");
            System.out.println("[1] List all Customers");
            System.out.println("[2] List a Customer");
            System.out.println("[3] Add a Customer");
            System.out.println("[4] Update a Customer Detail");
            System.out.println("[5] Delete a Customer");
            System.out.println("[00] Go back to the Main Menu");
            System.out.println("[99] Exit\n");

            selection = input.nextLine();

            switch (selection) {
                case "1":
                    System.out.println(customerDAO.getAllCustomers());
                    break;
                case "2":
                    System.out.println("Enter the customerID of the customer you wish to find: ");
                    int customerID = input.nextInt();
                    System.out.println(customerDAO.getCustomer(customerID));
                    break;
                case "3":
                    System.out.println("Enter First Name: ");
                    String firstName = input.nextLine();

                    System.out.println("Enter Last Name: ");
                    String lastName = input.nextLine();

                    System.out.println("Enter House Number: ");
                    String houseNumber = input.nextLine();

                    System.out.println("Enter addresses.Address Line 1: ");
                    String addressLine1 = input.nextLine();

                    System.out.println("Enter addresses.Address Line 2: ");
                    String addressLine2 = input.nextLine();

                    System.out.println("Enter Country: ");
                    String country = input.nextLine();

                    System.out.println("Enter Post Code: ");
                    String postCode = input.nextLine();

                    System.out.println("Enter Telephone Number: ");
                    String telephoneNumber = input.nextLine();

                    Address address = new Address(houseNumber, addressLine1, addressLine2, country, postCode);
                    Customer customer = new Customer(0, firstName, lastName, address, telephoneNumber);
                    customerDAO.addCustomer(customer);
                    System.out.println("Customer Added");
                    break;
                case "4":
                    System.out.println("Enter the customerID of the customer you wish to update: ");
                    int customerIDUpdate = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter New First Name: ");
                    String firstNameUpdate = input.nextLine();

                    System.out.println("Enter New Last Name: ");
                    String lastNameUpdate = input.nextLine();

                    System.out.println("Enter New House Number: ");
                    String houseNumberUpdate = input.nextLine();

                    System.out.println("Enter New Address Line 1: ");
                    String addressLine1Update = input.nextLine();

                    System.out.println("Enter New Address Line 2: ");
                    String addressLine2Update = input.nextLine();

                    System.out.println("Enter New Country: ");
                    String countryUpdate = input.nextLine();

                    System.out.println("Enter New Post Code: ");
                    String postCodeUpdate = input.nextLine();

                    System.out.println("Enter New Telephone Number: ");
                    String telephoneNumberUpdate = input.nextLine();;

                    Address addressUpdate = new Address(houseNumberUpdate, addressLine1Update, addressLine2Update, countryUpdate, postCodeUpdate);
                    Customer customerUpdate = new Customer(customerIDUpdate, firstNameUpdate, lastNameUpdate, addressUpdate, telephoneNumberUpdate);
                    customerDAO.updateCustomer(customerUpdate);
                    System.out.println("Customer Updated");
                    break;
                case "5":
                    System.out.println("Enter the customerID of the customer you wish to delete: ");
                    int customerIDDelete = input.nextInt();
                    input.nextLine();
                    customerDAO.deleteCustomer(customerIDDelete);
                    System.out.println("Customer Deleted");
                    break;
                case "00":
                    Controller.menu();
                    break;
                case "99":
                    System.out.println("Exiting...");
                    break;
                default:
            }
        } while (!selection.equals("99"));
        input.close();
    }

  /**
* Runs the web server.
* @throws IOException
*/
    public static void webServer() throws IOException {
        final int PORT = 8080;

        System.out.println("Hello world!");

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);

        server.createContext("/login", new Login());
        server.createContext("/login/processLogin", new HandleLogin());

        server.createContext("/", new RootHandler() );
        server.createContext("/products", new DisplayProductLibrary() );
        server.createContext("/products/update", new UpdateHandler() );
        server.createContext("/products/processUpdateProduct", new ProcessUpdateProductHandler() );
        server.createContext("/products/delete", new DeleteHandler() );
        server.createContext("/products/add", new AddHandler());
        server.createContext("/products/processAddProduct", new ProcessAddProductHandler());
        server.createContext("/products/find", new FindProductSearch());
        server.createContext("/products/processFindProduct", new ProcessFindProductSearch());

        server.createContext("/customers", new DisplayCustomerLibrary() );
        server.createContext("/customers/update", new CustomerUpdateHandler() );
        server.createContext("/customers/processUpdateCustomer", new ProcessUpdateCustomerHandler() );
        server.createContext("/customers/delete", new CustomerDeleteHandler() );
        server.createContext("/customers/add", new CustomerAddHandler());
        server.createContext("/customers/processAddCustomer", new ProcessAddCustomerHandler());
        server.createContext("/customers/find", new FindCustomerSearch());
        server.createContext("/customers/processFindPCustomer", new ProcessFindCustomerSearch());

        server.createContext("/users", new DisplayUserLibrary() );
        server.createContext("/users/update", new UserUpdateHandler() );
        server.createContext("/users/processUpdateUsers", new ProcessUpdateUserHandler() );
        server.createContext("/users/delete", new UserDeleteHandler() );
        server.createContext("/users/add", new UserAddHandler());
        server.createContext("/users/processAddUsers", new ProcessAddUserHandler());
        server.createContext("/users/find", new FindUserSearch());
        server.createContext("/users/processFindPUsers", new ProcessFindUserSearch());

        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);
    }

  /**
* The menu function allows the user to select different options for managing products, customers, and the web.
* @throws SQLException 
* @throws IOException 
*/
    public static void menu() throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        String selection;
        do {
            System.out.println("_______________________");
            System.out.println("The Everything Store");
            System.out.println("Choose from these options");
            System.out.println("_________________________");
            System.out.println("[1] Product Menu");
            System.out.println("[2] Customer Menu");
            System.out.println("[3] Web Menu");
            System.out.println("[99] Exit\n");

            selection = input.nextLine();

            switch (selection) {
                case "1":
                    Controller.runProducts();
                    break;
                case "2":
                    Controller.runCustomer();
                    break;
                case "3":
                    Controller.webServer();
                    break;
                case "99":
                    System.out.println("Exiting...");
                    break;
                default:
            }
        } while (!selection.equals("99"));
        input.close();
    }
}

