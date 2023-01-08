import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ShowProductHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();




        UserDAO ud = new UserDAO();


        // Get param from URL
        Map <String,String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);
        int id = Integer.parseInt(params.get("id"));
        System.out.println(id);


        // Authorised Access

            try {
                Product product = products.getProductByID(id);
                out.write(
                        getHeader.get() +
                                "<h1>Product:" + product.getName() +"</h1>"


                );


                out.write(
                        "<div>" +
                                "<div class=\"form-row\">" +

                                "<span>ID: " + product.getID()  + "</span>" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<span>SKU: " + product.getSKU()  + "</span>" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<span>Category: " + product.getCategory()  + "</span>" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<span>Name: " + product.getName()  + "</span>" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<span>Description: " + product.getDescription()  + "</span>" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<span>Price: " + product.getPrice()  + "</span>" +
                                "</div>" +
                                "<div class=\"form-row\">" +
                                "<span>Stock: " + product.getStock()  + "</span>" +
                                "</div>" +
                                "</div>" +
                                "</div>"

                );

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

