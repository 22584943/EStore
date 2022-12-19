import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class EditCustomerHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();




        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();

        CustomerDAO cd = new CustomerDAO();
        // Get param from URL
        Map <String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);
        int id = Integer.parseInt(params.get("id"));
        System.out.println(id);


        // Authorised Access
        if (isLoggedIn) {
            try {
                Customer customer = cd.getCustomerByID(id);
                out.write(
                        getHeader.get() +
                                "<h1>Edit Customer:" + id +"</h1>"


                );


                out.write(
                        "<form class=\"edit-product-form\" action=\"edit-customer-handler\" method=\"post\">" +
                                "<div class=\"form-row\">" +
                                    "<label>ID</label>"  +
                                    "<input name=\"id\" type=\"hidden\" value=\"" + customer.getID()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                    "<label>Name</div>" +
                                    "<input name=\"name\" type=\"text\" value=\""+ customer.getName()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                    "<label>Address</label>" +
                                    "<input name=\"address\"type=\"text\" value=\""+ customer.getAddress()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                    "<label>Email</label>" +
                                    "<input name=\"email\" type=\"text\" value=\""+ customer.getEmail()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                    "<label>Phone</label>" +
                                    "<input name=\"telephone\" type=\"text\" value=\""+ customer.getTelephone()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                    "<button type=\"submit\">Update</button>" +
                                "</div>" +
                                "</form>"

                );

                out.write(


                        "</tbody>" +
                                "</table>" +
                                "</div>" +
                                "</body>" +
                                "</html>"
                );
                out.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // Unauthorised access
        } else {

            out.write(
                    getHeader.get() +
                            "<h1>Access Denied</h1>" +
                            "</body>" +
                            "</html>"
            );
            out.close();
        }



    }

}