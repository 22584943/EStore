package handlers;

import java.io.*;
import utils.Util;
import DAOs.BasketDAO;
import DAOs.UserDAO;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import utils.getHeader;

import java.sql.SQLException;
import java.util.Map;

public class DeleteBasketItemHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {


        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        BasketDAO basketDAO = new BasketDAO();
        boolean productDeleted = false;
        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isCustomerLoggedIn = ud.isLoggedIn("Customer");

        Map<String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());


        // Get productID param
        int productToDeleteID = Integer.parseInt(params.get("id"));
        if (isCustomerLoggedIn) {


            try {
                productDeleted = basketDAO.deleteBasketItem(productToDeleteID);
                System.out.println(productDeleted);
                if (productDeleted) {
                    out.write(
                            getHeader.get() +
                                    "<div class=\"basket-wrapper\">" +
                                    "<h1>Product removed from basket</h1>" +
                                    "<a href=\"/basket\">Back to basket</a>"


                    );
                } else {
                    out.write(
                            getHeader.get() +
                                    "<h2>Error: please try again</h2>"

                    );
                }

            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }


            out.close();
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