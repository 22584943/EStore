import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DeleteCustomerHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();




        UserDAO ud = new UserDAO();
        CustomerDAO cd = new CustomerDAO();
        // Check if logged in
        boolean isAdminLoggedIn = ud.isLoggedIn("Admin");

        // Get param from URL
        Map <String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);
        // Get SKU param
        int id = Integer.parseInt(params.get("id"));
        System.out.println(id);


        // Authorised Access
        if (isAdminLoggedIn) {
            try {
                Customer customer = cd.getCustomerByID(id);
                out.write(
                        getHeader.get() +
                                "<h1>Edit Product:" + customer.getName() +"</h1>"



                );


                out.write(
                        "<table>" +
                                "<tr>" +
                                "<td>" + customer.getID() +"</td>" +
                                "<td>" + customer.getName() +"</td>" +
                                "<td>" + customer.getAddress() +"</td>" +
                                "<td>" + customer.getEmail() +"</td>" +
                                "<td>" + customer.getTelephone() +"</td>" +
                                "</tr>" +
                                "</table>" +
                                "<form class=\"edit-product-form\" action=\"delete-customer-handler\" method=\"post\">" +
                                "<div class=\"form-row\">" +
                                "<input name=\"id\" type=\"hidden\" value=\"" + customer.getID()  +"\" />" +
                                "<button type=\"submit\">Confirm Delete</button>" +
                                "</div>" +
                                "</form>"

                );

                out.write(



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