import java.io.*;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Map;

public class AddBasketHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {


        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        BasketDAO basketDAO = new BasketDAO();
        // print the params for debugging
        System.out.println(params);
        // Get SKU param
        int productID= Integer.parseInt(params.get("id"));
        int quantity = Integer.parseInt(params.get("quantity"));
        int currentStock = Integer.parseInt(params.get("currentStock"));
        try {
            // verify user
            boolean addToBasketSuccess = basketDAO.addProductToBasket(productID, quantity, currentStock); // add to database
            String outputMessage = addToBasketSuccess ? "Product Added" : "Error: Please try again";

            out.write(
                    "<html>" +
                            getHeader.get() +
                            "<body>" +
                            "<div class=\"flex-center flex-down\">" +
                            "<h1>" + outputMessage + "</h1>" +

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
