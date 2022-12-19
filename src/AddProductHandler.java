import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class AddProductHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();




        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();




        // Authorised Access
        if (isLoggedIn) {
            out.write(
                    getHeader.get() +
                            "<h1>Add Product</h1>"


            );


            out.write(
                    "<form class=\"edit-product-form\" action=\"add-product-handler\" method=\"post\">" +
                            "<div class=\"form-row\">" +
                            "<label>SKU</div>" +
                            "<input name=\"SKU\" type=\"text\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Category</label>" +
                            "<input name=\"category\"type=\"text\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Product Name</label>" +
                            "<input name=\"name\" type=\"text\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Description</label>" +
                            "<input name=\"description\" type=\"text\" />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Price</label>" +
                            "<input name=\"price\"type=\"number\"  />" +
                            "</div>" +
                            "<div class=\"form-row\">" +
                            "<label>Stock</label>" +
                            "<input name=\"stock\" type=\"number\"  />" +
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