import java.io.*;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Map;

public class LoginFormHandler implements HttpHandler{
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

        System.out.println("about to get data");

        String username = postData.get("username");
        String password = postData.get("password");





        System.out.println("about to create user"); // Debugging message

        boolean passwordMatch;
        try {
            passwordMatch = verifyPassword.verify(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }


        if (passwordMatch==true) {


            try {
                // verify user
                boolean loginSuccessful = UserDAO.verifyUser(username); // add to database
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
        }
        out.close();

    }
}
