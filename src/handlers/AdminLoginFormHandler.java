package handlers;

import java.io.*;

import DAOs.UserDAO;
import utils.Password;
import utils.Util;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import utils.getHeader;

import java.sql.SQLException;
import java.util.Map;

public class AdminLoginFormHandler implements HttpHandler{
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


        String username = postData.get("username");
        String password = postData.get("password");

        // Get stored hashed password
        String storedPassword = null;
        try {
            storedPassword = UserDAO.getStoredPassword(username, "Admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    System.out.println("sp: "+storedPassword);
        // AUTHENTICATION - verify password

        // must initialise to false, to avoid Util.Password crash
        boolean passwordMatch = false;
        if (storedPassword != null) {
            passwordMatch = Password.checkPassword(password, storedPassword);
        }

        if (passwordMatch) {


            try {
                // verify user
                boolean loginSuccessful = UserDAO.verifyLogin(username, "Admin"); // add to database
                String outputMessage = loginSuccessful ? "Welcome, " + username : "Error: Incorrect username or password";
                String showLinks = loginSuccessful ? "<a href=\"products\">View products</a><a href=\"customers\">View customers</a>" : "";
                out.write(
                        "<html>" +
                                getHeader.get() +
                                "<body>" +
                                "<div class=\"flex-center flex-down\">" +
                                "<h1>" + outputMessage + "</h1>" +
                                showLinks +

                                "</div>" +
                                "</body>" +
                                "</html>"
                );
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        } else {
            out.write(
                    "<html>" +
                            getHeader.get() +
                            "<body>" +
                            "<div class=\"flex-center flex-down\">" +
                            "<h1>" + "Access denied: Incorrect password " + "</h1>" +

                            "</div>" +
                            "</body>" +
                            "</html>"
            );
        }
        out.close();

    }
}
