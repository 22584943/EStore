import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class LoginFormHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {


        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number


        UserDAO users = new UserDAO();

        System.out.println("about to get data");

        String username = params.get("username");
        String password = params.get("password");


        System.out.println("about to create user"); // Debugging message
        User user = new User(username, password);
        System.out.println("user to Add" + user);

        try {
            // verify user
            boolean loginSuccessful = UserDAO.verifyUser(user); // add to database
            String outputMessage = loginSuccessful ?  "Welcome "+ username : "Error: Incorrect username or password";
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
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }

        out.close();

    }
}
