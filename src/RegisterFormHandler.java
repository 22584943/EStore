import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class RegisterFormHandler implements HttpHandler{
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
            // attempt to add user to db, get response message
           boolean addSuccessful = UserDAO.addUser(user); // add to database
            String outputMessage = addSuccessful ?  "User "+ username + " successfully added" : "Error: Unable to add user to database";

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
