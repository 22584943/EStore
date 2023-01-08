package handlers;

import java.io.OutputStreamWriter;

import models.Address;
import models.Customer;
import utils.Util;
import DAOs.CustomerDAO;
import DAOs.ProductDAO;
import DAOs.UserDAO;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import utils.getHeader;

import java.sql.SQLException;
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
        boolean isAdminLoggedIn = ud.isLoggedIn("Admin");

        CustomerDAO cd = new CustomerDAO();
        // Get param from URL
        Map <String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);
        int id = Integer.parseInt(params.get("id"));
        System.out.println(id);


        // Authorised Access
        if (isAdminLoggedIn) {
            try {
                Customer customer = cd.getCustomerByID(id);
                Address cAddress = customer.getAddress();
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
                                    "<label>House Number</label>" +
                                    "<input name=\"houseNumber\"type=\"text\" value=\""+ cAddress.getHouseNumber()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<label>First Line of Address</label>" +
                                "<input name=\"firstLine\"type=\"text\" value=\""+ cAddress.getFirstLine()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<label>Town/City</label>" +
                                "<input name=\"town\"type=\"text\" value=\""+ cAddress.getTown()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<label>Postcode</label>" +
                                "<input name=\"postcode\"type=\"text\" value=\""+ cAddress.getPostcode()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<label>County/State</label>" +
                                "<input name=\"countyState\"type=\"text\" value=\""+ cAddress.getCountyState()  +"\" />" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<label>Country</label>" +
                                "<input name=\"country\"type=\"text\" value=\""+ cAddress.getCountry()  +"\" />" +
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