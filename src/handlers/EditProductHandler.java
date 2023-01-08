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

public class EditProductHandler implements HttpHandler{
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
        String paramSKU = params.get("SKU");
        int id = Integer.parseInt(params.get("id"));
        System.out.println(id);
        String category = params.get("category");


        // Authorised Access
        if (isAdminLoggedIn) {
            try {
                Product product = products.getProductByID(id);
                out.write(
                        getHeader.get() +
                                "<h1>Edit Product:" + paramSKU +"</h1>"


                );

                    String showEdit = isAdminLoggedIn ? "<td><a href=\"/products/edit?SKU=" + product.getSKU() +"\">Edit</a></td>" : "";
                    out.write(
                            "<form class=\"edit-product-form\" action=\"edit-product-handler\" method=\"post\">" +
                                    "<div class=\"form-row\">" +
                                        "<label>ID</label>"  +
                                    "<input name=\"id\" type=\"hidden\" value=\"" + product.getID()  +"\" />" +
                                    "</div>" +
                                    "<div class=\"form-row\">" +
                                        "<label>SKU</div>" +
                                        "<input name=\"SKU\" type=\"text\" value=\""+ product.getSKU()  +"\" />" +
                                    "</div>" +
                                    "<div class=\"form-row\">" +
                                        "<label>Category</label>" +
                                        "<input name=\"category\"type=\"text\" value=\""+ product.getCategory()  +"\" />" +
                                    "</div>" +
                                    "<div class=\"form-row\">" +
                                        "<label>Product Name</label>" +
                                        "<input name=\"name\" type=\"text\" value=\""+ product.getName()  +"\" />" +
                                    "</div>" +
                                    "<div class=\"form-row\">" +
                                        "<label>Description</label>" +
                                        "<input name=\"description\" type=\"text\" value=\""+ product.getDescription()  +"\" />" +
                                    "</div>" +
                                    "<div class=\"form-row\">" +
                                        "<label>Price</label>" +
                                        "<input name=\"price\"type=\"number\" value=\""+ product.getPrice()  +"\" />" +
                                    "</div>" +
                                    "<div class=\"form-row\">" +
                                        "<label>Stock</label>" +
                                        "<input name=\"stock\" type=\"number\" value=\""+ product.getStock()  +"\" />" +
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