import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class ProductAddHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        ProductDAO products = new ProductDAO();
        ArrayList<Product> coll;

        try {
            coll = products.getProducts();
            out.write(
                    "<html>" +
                            "<style>h1 {color: blue;}</style>" +
                            "<head> <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\"><title>Customers </title> </head>" +
                            "<body>" +
                            "<h1>Here be products...</h1>" +
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
                out.write(
                        "<tr>" +
                                "<td>" + p.getID() +"</td>" +
                                "<td>" + p.getSKU() +"</td>" +
                                "<td>" + p.getCategory() +"</td>" +
                                "<td>" + p.getName() +"</td>" +
                                "<td>" + p.getDescription() +"</td>" +
                                "<td>" + p.getPrice() +"</td>" +
                                "<td>" + p.getStock() +"</td>" +
                                "</tr>"

                );
            }
            out.write(


                    "</tbody>" +
                            "</table>" +
                            "</body>" +
                            "</html>"
            );
            out.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}