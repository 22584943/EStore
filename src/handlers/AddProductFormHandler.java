package handlers;

import java.io.*;

import DAOs.ProductDAO;
import DAOs.UserDAO;
import models.Product;
import utils.Util;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import utils.getHeader;

import java.sql.SQLException;
import java.util.Map;

public class AddProductFormHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {


        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        // Get POST data
        // See https://stackoverflow.com/questions/10393879/how-to-get-an-http-post-request-body-as-a-java-string-at-the-server-side
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
// From now on, the right way of moving from bytes to utf-8 characters:

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        // map POST data to key/values using Util.Util.requestStringToMap
        Map<String, String> postData = Util.requestStringToMap(buf.toString());
        br.close();
        isr.close();

// The resulting string is: buf.toString()
// and the number of BYTES (not utf-8 characters) from the body is: buf.length()


        UserDAO users = new UserDAO();
        ProductDAO productDAO = new ProductDAO();
        String SKU = postData.get("SKU");
        String category = postData.get("category");
        String name = postData.get("name");
        String description = postData.get("description");
        double price = Double.parseDouble(postData.get("price"));
        int stock = Integer.parseInt(postData.get("stock"));

        Product updatedProduct = new Product(SKU, category, name, description, price, stock);
        try {
            // verify user
            boolean updateProductSuccess = productDAO.addProduct(updatedProduct); // add to database
            String outputMessage = updateProductSuccess ? "Product Added" : "Error: Please try again";

            out.write(
                    "<html>" +
                            getHeader.get() +
                            "<body>" +
                            "<div class=\"flex-center flex-down\">" +
                            "<h1>" + outputMessage + "</h1>" +
                            "<a href=\"/products\">Back to products</a>" +
                            "</div>" +
                            "</body>" +
                            "</html>"
            );
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }

        out.write(
                "<html>" +
                        getHeader.get() +
                        "<body>" +
                        "<div class=\"flex-center flex-down\">" +
                        "<h1>" + "Problem encountered. Please try again" + "</h1>" +

                        "</div>" +
                        "</body>" +
                        "</html>"
        );

        out.close();
    }

}
