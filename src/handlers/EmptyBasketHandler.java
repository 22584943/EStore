package handlers;

import java.io.*;

import DAOs.BasketDAO;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import utils.getHeader;

import java.sql.SQLException;

public class EmptyBasketHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {


        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));


        BasketDAO basketDAO = new BasketDAO();


        try {
            // empty basket
            boolean emptyBasketSuccess = basketDAO.emptyBasket();
            String outputMessage = emptyBasketSuccess ? "Basket emptied" : "Error: Please try again";

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
