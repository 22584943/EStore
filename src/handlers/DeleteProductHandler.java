package handlers;

import java.io.OutputStreamWriter;

import DAOs.ProductDAO;
import DAOs.UserDAO;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import models.Product;
import utils.Util;
import utils.getHeader;

import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DeleteProductHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();




        UserDAO ud = new UserDAO();
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
                Product product = products.getProductByID(id);
                out.write(
                        getHeader.get() +
                                "<h1>Delete Product:" + product.getName() +"</h1>"



                );


                out.write(
                        "<table>" +
                        "<tr>" +
                                "<td>" + product.getID() +"</td>" +
                                "<td>" + product.getSKU() +"</td>" +
                                "<td>" + product.getCategory() +"</td>" +
                                "<td>" + product.getName() +"</td>" +
                                "<td>" + product.getDescription() +"</td>" +
                                "<td>" + product.getPrice() +"</td>" +
                                "<td>" + product.getStock() +"</td>" +
                                "</tr>" +
                                "</table>" +
                        "<form class=\"edit-product-form\" action=\"delete-product-handler\" method=\"post\">" +
                                "<div class=\"form-row\">" +
                                    "<input name=\"id\" type=\"hidden\" value=\"" + product.getID()  +"\" />" +
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