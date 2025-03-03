import java.io.*;
import java.util.*;

/**
 *
 * Cookies class is used to create and read cookies
 */
public class Cookies {

  /**
creates cookies with user details
@param username
@param password
@throws IOException if an error occurs while writing to the file
*/
    public static void cookies(String username, String password) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("cookies.txt"));
        out.write(username);
        out.write("\n" + password);
        out.close();
    }

  /**
reads the cookies and returns the details
@return ArrayList 
@throws IOException
*/
    public static ArrayList<String> readCookies() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("cookies.txt"));
        ArrayList<String> adminDetails = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null){
            adminDetails.add(line);
        }
        return adminDetails;
    }
}
