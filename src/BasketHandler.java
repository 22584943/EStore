import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class BasketHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        BasketDAO basketDAO = new BasketDAO();
        ArrayList<Product> coll;



        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();
        // Condition button render

        try {

            coll = basketDAO.getBasket();

            out.write(
                    getHeader.get() +
                            "<div class=\"basket-wrapper\">" +
                            "<h1>My Basket</h1>"


            );

            // Show table headings only if basket has items
            if (coll.size() > 0) {
                out.write(
                        "<a href=\"basket/empty\">Empty basket</a>" +
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
                                "<th class=\"col\">Quantity</th>" +
                                "<th class=\"col\">Total</th>" +
                                "</tr>" +
                                "</thead>" +
                                "<tbody>"

                );

            } else {
                out.write(
                        "<h2>Your basket is empty</h2>"

                );
            }


            double totalPrice = 0;
            DecimalFormat formatter = null;
            for (Product p : coll) {
                formatter = new DecimalFormat("0.00");

                double fPrice = (double) p.getPrice() / 100;
                String formattedPrice = formatter.format(fPrice);

                double fItemTotalPrice = (double) p.getPrice() * p.getQuantityOrdered() / 100;
                String formattedItemTotalPrice = formatter.format(fItemTotalPrice);
                String showDelete = isLoggedIn ? "<td><a href=\"/products/delete?id=" + p.getID() + "\">Delete</a></td>" : "";
                totalPrice += fItemTotalPrice;
                out.write(
                        "<tr>" +
                                "<td>" + p.getID() + "</td>" +
                                "<td>" + p.getSKU() + "</td>" +
                                "<td>" + p.getCategory() + "</td>" +
                                "<td>" + p.getName() + "</td>" +
                                "<td>" + p.getDescription() + "</td>" +
                                "<td>&#163;" + formattedPrice + "</td>" +
                                "<td>" + p.getQuantityOrdered() + "</td>" +
                                "<td>&#163;" + formattedItemTotalPrice + "</td>" +
                                showDelete +
                                "</tr>"

                );


            }
            String formattedTotalPrice = formatter.format(totalPrice);
            if (coll.size() > 0) {
                out.write(
                        "</tbody>" +
                                "</table>" +
                                "<div class=\"checkout-total-cont\">" +
                                "<span>Total: &#163;" + formattedTotalPrice + "</span>" +
                                "<a class=\"checkout-btn\" href=\"/basket/checkout\">Checkout Now</a>" +
                                "</div>" +
                                "</div>");
            }
            out.write(


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