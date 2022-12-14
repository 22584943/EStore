import java.io.*;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LogoutHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));


        UserDAO ud = new UserDAO();
        // Logs out
        ud.logout();
        out.write(
                getHeader.get() +


                        "<div class=\"flex-center flex-down\">" +
                            "<h1>You have successfully logged out</h1>" +
                        "</div>" +
                        "    </body>" +
                        "</html>"
        );
        out.close();




    }

}