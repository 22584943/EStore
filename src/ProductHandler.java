import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class ProductHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();
        String collJSON = "";
        ArrayList<Product> coll;



        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();
        // Condition button render
        String AddDeleteColumn = isLoggedIn ? "<div class=\"cell-cont\"></div><div class=\"cell-cont\"></div>" : "";

        // Autocomplete script


        try {
            String showAdd = isLoggedIn ? "<div><a href=\"/products/add\"><span>Add Product</span></a></div>" : "";
            coll = products.getProducts();
            collJSON = products.getProductsJSON();
            System.out.println(collJSON);
            String autoCompleteScript = "const searchInput = document.getElementById(\"searchInput\");\n" +
                    "\n" +
                    "const dropdownCont = document.getElementsByClassName(\"dropdown-cont\")[0]\n" +
                    "const dropdownResults = document.getElementsByClassName(\"dropdown-results\")[0]\n" +
                    "\n" +
                    "let searchResults = '" + collJSON + "'" + "\n"+
                    "searchResults = searchResults.substring(0, searchResults.length - 2)" + "\n" +
                    "searchResults = \"[\" + searchResults + \"]\"" + "\n" +
                    "console.log(JSON.parse(searchResults))\n" +
//                    "searchResults = searchResults.replace('[','');"+
//                    "searchResults = searchResults.replace(']','');"+
//                    "console.log(searchResults)\n" +
//                    "searchResults = searchResults.split(\"}, \")\n" +
//                    "console.log(searchResults)\n" +
                    "\n" +
                    "searchInput.addEventListener(\"input\", (e)=> {\n" +
                    "    console.log(e.target.value)\n" +
                    "})";
//            "let searchResults = \"" + coll + "\"\n" +
            ArrayList<String> categories = products.getCategories();
            out.write(
                    getHeader.get() +
                            "<div class=\"wrapper\">" +
                            "<h2>Product Listings</h2>" +
                            "<form class=\"search-form\" action=\"products\\product-search\" method=\"get\">\n" +
                            "            <select name=\"searchType\">\n" +
                            "                <option value=\"id\">id</option>\n" +
                            "                <option value=\"SKU\">SKU</option>\n" +
                            "                <option value=\"name\">name</option>\n" +
                            "                <option value=\"category\">category</option>" +
                            "                <option value=\"description\">description</option>\n" +
                            "            </select>\n" +
                            "            <div class=\"dropdown-cont\"><input id=\"searchInput\" name=\"query\" type=\"text\" /><ul id=\"dropdown-results\"></ul></div>\n" +
                            "           <button type=\"submit\">Search</button>" +
                            "        </form>" +
                            "       <div class=\"category-btn-cont\"><span>Categories: </span>"
            );
            for (String category: categories) {


                out.write(

                        "<a href=\"products/product-search?searchType=category&query=" + category +"\">"+ category +"</a>"


                );
            }
            out.write(
                                "</div>" +
                            "<div class=\"table-cont\">" +

                                "<div class=\"tr\">" +
                                "<div class=\"cell-cont\">ID</div>" +
                                "<div class=\"cell-cont\">SKU</div>" +
                                "<div class=\"cell-cont\">Category</div>" +
                                "<div class=\"cell-cont\">Name</div>" +
                                "<div class=\"cell-cont\">Description</div>" +
                                "<div class=\"cell-cont\">Price</div>" +
                                "<div class=\"cell-cont\">Stock</div>" +
                                "</div>"

            );



            for (Product p: coll) {
                DecimalFormat formatter = new DecimalFormat("0.00");

                String formattedPrice = formatter.format(p.getPrice());
                // Keep add/delete in conditional cell-col render, to maintain table styling
                String showEditOrAddBtn = isLoggedIn ? "<span class=\"cell-col edit-btn-cell\"><a class=\"flex\" href=\"/products/edit?id=" + p.getID() +"\"><i class=\"bi bi-pencil-square\"></i><span>Edit</span></a><a class=\"flex\" href=\"/products/delete?id=" + p.getID() +"\"/><i class=\"bi bi-trash-fill\"></i></i><span>Delete</span></a>" : "<span class=\"product-basket-cell cell-cont\"><form action=\"/basket/add\" method=\"get\"><input type=\"hidden\" name=\"id\" value=\""+p.getID() + "\"/><input type=\"hidden\" name=\"currentStock\" value=\"" +p.getStock() + "\"/><input type=\"number\" value=\"1\" name=\"quantity\"/><button type=\"submit\">Add</button></form></span>";

                out.write(
                        "<div class=\"tr\">" +
                                "<span class=\"cell-cont \">" + p.getID() +"</span>" +
                                "<span class=\"cell-cont \">" + p.getSKU() +"</span>" +
                                "<span class=\"cell-cont \">" + p.getCategory() +"</span>" +
                                "<span class=\"cell-cont \">" + p.getName() +"</span>" +
                                "<span class=\"cell-cont \">" + p.getDescription() +"</span>" +
                                "<span class=\"cell-cont \">&#163;" + formattedPrice +"</span>" +
                                "<span class=\"cell-cont \">" + p.getStock() +"</span>" +

                                showEditOrAddBtn +

                                "</div>"

                );
            }
            out.write(


                        "</div>" +
                            "</div>" +
                            showAdd +
                                "<script>" +
                                autoCompleteScript +
                                "</script>"+
                            "</body>" +
                            "</html>"
            );
            out.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}