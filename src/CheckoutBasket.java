import java.io.*;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Map;

public class CheckoutBasket implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {


        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        ProductDAO productDAO = new ProductDAO();
        BasketDAO basketDAO = new BasketDAO();
        ArrayList<Product> basket = null;
        try {
            basket = basketDAO.getBasket();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // fetch response object with message. empty basket
            ResponseObject checkoutBasketResponse = basketDAO.checkoutBasket(basket);
            StringBuilder outputMessage = new StringBuilder(checkoutBasketResponse.success ? "Checkout successful" : "Error: Please try again");
            if (checkoutBasketResponse.errorMessage.length() > 0) {
                String[] errorMsg = checkoutBasketResponse.errorMessage.split(";");
                ArrayList<String> productsOutOfStock = new ArrayList<String>();

                for (String error : errorMsg) {
                    String id = error.split(", ")[0];
                    productsOutOfStock.add(id);
                }

                for (String productID : productsOutOfStock) {
                    outputMessage.append("<li>").append(productDAO.getProductByID(Integer.parseInt(productID)).getName()).append("out of Stock</li>");
                }

            }
            out.write(
                    "<html>" +
                            getHeader.get() +
                            "<body>" +
                            "<div class=\"flex-center flex-down\">" +
                            "<ul>" + outputMessage + "</ul>" +

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
