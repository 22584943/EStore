package handlers;

import java.io.OutputStreamWriter;

import DAOs.ProductDAO;
import DAOs.UserDAO;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import utils.getHeader;

import java.io.BufferedWriter;
import java.io.IOException;

public class AddProductHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();




        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isAdminLoggedIn = ud.isLoggedIn("Admin");




        // Authorised Access
        if (isAdminLoggedIn) {
            out.write(
                    getHeader.get() +
                            "<h1>Add Product</h1>"


            );


            out.write(
                    "<form class=\"edit-product-form\" action=\"add-product-handler\" method=\"post\">" +
                            "<div class=\"form-row\">" +
                            "<label>SKU</div>" +
                            "<input required name=\"SKU\" type=\"text\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Category</label>" +
                            "<input required name=\"category\"type=\"text\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Product Name</label>" +
                            "<input required name=\"name\" type=\"text\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Description</label>" +
                            "<input required name=\"description\" type=\"text\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Price</label>" +
                            // add step="any" to allow decimal values
                            "<input required name=\"price\"type=\"number\" step=\"any\" min=\"0\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Stock</label>" +
                            "<input required name=\"stock\" type=\"number\" min=\"0\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<button type=\"submit\">Add Product</button>" +
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