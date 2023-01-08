import java.io.*;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.Map;

public class CustomerLoginFormHandler implements HttpHandler{
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
        // map POST data to key/values using Util.requestStringToMap
        Map <String,String> postData = Util.requestStringToMap(buf.toString());
        br.close();
        isr.close();

// The resulting string is: buf.toString()
// and the number of BYTES (not utf-8 characters) from the body is: buf.length()




        UserDAO users = new UserDAO();


        String email = postData.get("email");
        String password = postData.get("password");

        // Get stored hashed password
        String storedPassword = null;
        try {
            storedPassword = UserDAO.getStoredPassword(email, "Customer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("sp: "+storedPassword);
        // AUTHENTICATION - verify password
        // must initialise to false, to avoid Password crash
        boolean passwordMatch = false;
        if (storedPassword != null) {
            passwordMatch = Password.checkPassword(password, storedPassword);
        }


        if (passwordMatch) {


            try {
                // verify user
                boolean loginSuccessful = UserDAO.verifyLogin(email, "Customer");
                String outputMessage = loginSuccessful ? "Welcome, " + email : "Error: Incorrect username or password";

                out.write(
                        "<html>" +
                                getHeader.get() +
                                "<body>" +
                                "<div class=\"flex-center flex-down\">" +
                                "<h1>" + outputMessage + "</h1>" +
                                "<a href=\"/products\">View products</a>" +

                                "</div>" +
                                "</body>" +
                                "</html>"
                );
                out.close();
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        } else {
            out.write(
                    "<html>" +
                            getHeader.get() +
                            "<body>" +
                            "<div class=\"flex-center flex-down\">" +
                            "<h1>" + "Error: Incorrect username or password" + "</h1>" +

                            "</div>" +
                            "</body>" +
                            "</html>"
            );
            out.close();
        }

    }
}
