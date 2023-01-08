package handlers;

import java.io.OutputStreamWriter;

import DAOs.ProductDAO;
import DAOs.UserDAO;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import models.Product;
import utils.getHeader;

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
        boolean isAdminLoggedIn = ud.isLoggedIn("Admin");
        // Condition button render
        String AddDeleteColumn = isAdminLoggedIn ? "<div class=\"cell-cont\"></div><div class=\"cell-cont\"></div>" : "";

        // Autocomplete script


        try {
            String showAdd = isAdminLoggedIn ? "<div><a href=\"/products/add\"><span>Add Product</span></a></div>" : "";
            coll = products.getProducts();
            collJSON = products.getProductsJSON();
            System.out.println(collJSON);
//            String autoCompleteScript = "const searchInput = document.getElementById(\"searchInput\");\n" +
//                    "\n" +
//                    "const dropdownCont = document.getElementsByClassName(\"dropdown-cont\")[0]\n" +
//                    "const dropdownResults = document.getElementById(\"dropdown-results\")\n" +
//                    "let searchType = document.getElementById(\"searchType\").value\n" +
//                    "\n" +
//                    "let matchedResults = []" + "\n" +
//                    "let searchResultsData = '" + collJSON + "'" + "\n"+
//                    "let searchResultsArr = []" + "\n"+
//                    "searchResultsData = searchResultsData.substring(0, searchResultsData.length - 2)" + "\n" +
//                    "searchResultsData = \"[\" + searchResultsData + \"]\"" + "\n" +
//                    "searchResultsArr = JSON.parse(searchResultsData)\n" +
//                    "console.log(searchResultsArr)" +
////
//                    "\n" +
//                    "searchInput.addEventListener(\"input\", (e)=> {\n" +
//                    "    searchType = document.getElementById(\"searchType\").value\n" +
//                    "    let inputText = e.target.value\n" +
//                    "    console.log(searchType)\n" +
//                    // clear results each time to avoid duplications
//                    " matchedResults = []\n" +
//                    "dropdownResults.innerHTML = \"\"\n" +
//                    "    for (let i=0; i<searchResultsArr.length; i++) {\n" +
//// only show when inputText length > 0
//                    "        if (searchResultsArr[i][searchType].includes(inputText) && inputText.length > 0) {\n" +
//                    "            matchedResults.push(searchResultsArr[i])" +
//                    "        }\n" +
//                    "    }\n" +
//                    "\n" +
//                    "    for (let i=0; i<matchedResults.length; i++) {\n" +
//                    "            dropdownResults.innerHTML += `<li><a href=\"/product?id=${matchedResults[i][\"id\"]}\">${matchedResults[i][searchType]}</a></li>`\n" +
//
//                    "    }\n" +
//                    "})"
//                    ;
//            "let searchResults = \"" + coll + "\"\n" +
            ArrayList<String> categories = products.getCategories();
            out.write(
                    getHeader.get() +
                            "<div class=\"wrapper\">" +
                            "<h2>Product Listings</h2>" +
                            "<form class=\"search-form\" action=\"products\\product-search\" method=\"get\">\n" +
                            "            <select id=\"searchType\" name=\"searchType\">\n" +
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
                String showEditOrAddBtn = isAdminLoggedIn ? "<span class=\"cell-col edit-btn-cell\"><a class=\"flex\" href=\"/products/edit?id=" + p.getID() +"\"><i class=\"bi bi-pencil-square\"></i><span>Edit</span></a><a class=\"flex\" href=\"/products/delete?id=" + p.getID() +"\"/><i class=\"bi bi-trash-fill\"></i></i><span>Delete</span></a>" : "<span class=\"product-basket-cell cell-cont\"><form action=\"/basket/add\" method=\"get\"><input type=\"hidden\" name=\"id\" value=\""+p.getID() + "\"/><input type=\"hidden\" name=\"currentStock\" value=\"" +p.getStock() + "\"/><input type=\"number\" value=\"1\" name=\"quantity\"/><button type=\"submit\">Add</button></form></span>";

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
//                                autoCompleteScript +
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