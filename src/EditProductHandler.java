import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class EditProductHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();
        ArrayList<Product> coll;



        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();

        // Get param from URL
        Map <String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);
        // Get SKU param
        String paramSKU = params.get("SKU");
        // Authorised Access
        if (isLoggedIn) {
            try {
                coll = products.getProducts();
                out.write(
                        getHeader.get() +
                                "<h1>Edit Product:" + paramSKU +"</h1>"

                );
                for (Product p: coll) {
                    String showEdit = isLoggedIn ? "<td><a href=\"/products/edit?SKU=" + p.getSKU() +"\">Edit</a></td>" : "";
                    out.write(
                            "<tr>" +
                                    "<td>" + p.getID() +"</td>" +
                                    "<td>" + p.getSKU() +"</td>" +
                                    "<td>" + p.getCategory() +"</td>" +
                                    "<td>" + p.getName() +"</td>" +
                                    "<td>" + p.getDescription() +"</td>" +
                                    "<td>" + p.getPrice() +"</td>" +
                                    "<td>" + p.getStock() +"</td>" +
                                    showEdit +
                                    "</tr>"

                    );
                }
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
            try {
                coll = products.getProducts();
                out.write(
                        getHeader.get() +
                                "<h1>Access Denied</h1>" +
                                "</body>" +
                                "</html>"
                );
                out.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    }

}