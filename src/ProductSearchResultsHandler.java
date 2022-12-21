import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProductSearchResultsHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();
        ArrayList<Product> coll;

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);
        // Get SKU param
        String searchType = params.get("searchType");
        String query = params.get("query");

        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();
        // Condition button render

        try {
            coll = products.searchProducts(searchType, query);
            out.write(
                    getHeader.get() +
                            "<h1>Here be products...</h1>" +
                            "<div class=\"table-cont\">" +
                            "<table class=\"table table-dark table-striped\">" +
                            "<thead>" +
                            "<tr>" +
                            "<th class=\"col\">ID</th>" +
                            "<th class=\"col\">SKU</th>" +
                            "<th class=\"col\">Category</th>" +
                            "<th class=\"col\">Name</th>" +
                            "<th class=\"col\">Description</th>" +
                            "<th class=\"col\">Price</th>" +
                            "<th class=\"col\">Stock</th>" +
                            "</tr>" +
                            "</thead>" +
                            "<tbody>"

            );
            for (Product p: coll) {
                String showEdit = isLoggedIn ? "<td><a href=\"/products/edit?id=" + p.getID() +"\">Edit</a></td>" : "";
                String showDelete = isLoggedIn ? "<td><a href=\"/products/delete?id=" + p.getID() +"\">Delete</a></td>" : "";

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
                                showDelete +
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



    }

}