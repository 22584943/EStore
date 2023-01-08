package handlers;

import java.io.*;

import DAOs.UserDAO;
import models.User;
import utils.Password;
import utils.Util;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import utils.getHeader;

import java.sql.SQLException;
import java.util.Map;

public class AdminRegisterFormHandler implements HttpHandler{
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

        String username = postData.get("username");
        String password = postData.get("password");


        System.out.println("about to create user"); // Debugging message

        // Create password hash
        String hash;
        hash = Password.hashPassword(password);
        User user = new User(username, hash);
        System.out.println("user to Add" + user);


        try {
            // attempt to add user to db, get response message
           boolean addSuccessful = UserDAO.addAdmin(user); // add to database
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
