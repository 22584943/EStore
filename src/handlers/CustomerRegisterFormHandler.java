package handlers;

import java.io.*;

import DAOs.UserDAO;
import models.Address;
import models.Customer;
import utils.Password;
import utils.Util;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import utils.getHeader;

import java.sql.SQLException;
import java.util.Map;

public class CustomerRegisterFormHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {


        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));


        // Get POST data
        // See https://stackoverflow.com/questions/10393879/how-to-get-an-http-post-request-body-as-a-java-string-at-the-server-side
        InputStreamReader isr =  new InputStreamReader(he.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);

// From now on, the right way of moving from bytes to utf-8 characters:

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        // map POST data to key/values using Util.Util.requestStringToMap
        Map <String,String> postData = Util.requestStringToMap(buf.toString());
        br.close();
        isr.close();

// The resulting string is: buf.toString()
// and the number of BYTES (not utf-8 characters) from the body is: buf.length()




        UserDAO users = new UserDAO();

        System.out.println("about to get data");

        String email = postData.get("email");
        String password = postData.get("password");
        String name = postData.get("name");
        String telephone = postData.get("telephone");
        String houseNum = postData.get("houseNum");
        String addressFirstLine = postData.get("addressFirstLine");
        String town = postData.get("town");
        String countyState = postData.get("countyState");
        String postcode = postData.get("postcode");
        String country = postData.get("country");
        Address address = new Address(houseNum, addressFirstLine, town, countyState, postcode, country);

        // Create password hash
        String hashedPassword;
        hashedPassword = Password.hashPassword(password);
        System.out.println("about to create user"); // Debugging message
        Customer customer = new Customer(name, address, email, telephone, hashedPassword);
        try {
            // attempt to add user to db, get response message
            boolean addSuccessful = UserDAO.addCustomer(customer); // add to database
            String outputMessage = addSuccessful ?  "User "+ email + " successfully added" : "Error: Unable to add user to database";

            out.write(
                    "<html>" +
                            getHeader.get() +
                            "<body>" +
                            "<div class=\"flex-center flex-down\">" +
                            "<h1>" + outputMessage + "</h1>"+
                            "<a href=\"login\">Login Now</a>" +
                            "</div>" +
                            "</body>" +
                            "</html>"
            );
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }

        out.close();

    }
}
