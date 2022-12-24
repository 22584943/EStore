import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class ProductHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();
        ArrayList<Product> coll;



        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();
        // Condition button render

        try {
            String showAdd = isLoggedIn ? "<div><a href=\"/products/add\"><span>Add Product</span></a></div>" : "";
            coll = products.getProducts();
            ArrayList<String> categories = products.getCategories();
            out.write(
                    getHeader.get() +
                            "<h1>Here be products...</h1>" +
                            "<form class=\"search-form\" action=\"products\\product-search\" method=\"get\">\n" +
                            "            <select name=\"searchType\">\n" +
                            "                <option value=\"id\">id</option>\n" +
                            "                <option value=\"SKU\">SKU</option>\n" +
                            "                <option value=\"name\">name</option>\n" +
                            "                <option value=\"category\">category</option>" +
                            "                <option value=\"description\">description</option>\n" +
                            "            </select>\n" +
                            "            <input name=\"query\" type=\"text\" />\n" +
                            "           <button type=\"submit\">Search</button>" +
                            "        </form>"
            );
            for (String category: categories) {


                out.write(

                        "<div><a href=\"products/product-search?searchType=category&query=" + category +"\">"+ category +"</a></div>"


                );
            }

            out.write(
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
                                "<td class=\"product-basket-cell\"><form action=\"/basket/add\" method=\"get\"><input type=\"hidden\" name=\"id\" value=\""+p.getID() + "\"/><input type=\"hidden\" name=\"currentStock\" value=\"" +p.getStock() + "\"/><input type=\"number\" value=\"1\" name=\"quantity\"/><button type=\"submit\">Add</button></form>" +
                                "</tr>"

                );
            }
            out.write(


                    "</tbody>" +
                            "</table>" +
                            "</div>" +
                            showAdd +
                            "</body>" +
                            "</html>"
            );
            out.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}