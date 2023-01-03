import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class CustomerHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        String response = "Welcome to OOP";
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        CustomerDAO customers = new CustomerDAO();
        ArrayList<Customer> coll = new ArrayList<Customer>();
        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();

        try {
            coll = customers.getAllCustomers();
            out.write(
                    getHeader.get() +
                            "<div class=\"table-cont\">" +
                            "<table class=\"table table-dark table-striped\">" +
                            "<thead>" +
                                "<tr>" +
                                    "<th class=\"col\">ID</th>" +
                                    "<th class=\"col\">Name</th>" +
                                    "<th class=\"col\">Address</th>" +
                                    "<th class=\"col\">Email</th>" +
                                    "<th class=\"col\">Telephone</th>" +
                                "</tr>" +
                            "</thead>" +
                            "<tbody>"

            );
            String showAdd = isLoggedIn ? "<div><a href=\"/customers/add\"><span>Add Customer</span></a></div>" : "";
            for (Customer c: coll) {
                Address cAddress = c.getAddress();
                String cAddressToString = cAddress.toString();
                // Condition button render
                String showEdit = isLoggedIn ? "<td><a href=\"/customers/edit?id=" + c.getID() + "\">Edit</a></td>" : "";
                String showDelete = isLoggedIn ? "<td><a href=\"/customers/delete?id=" + c.getID() +"\">Delete</a></td>" : "";
                out.write(
                        "<tr>" +
                                "<td>" + c.getID() +"</td>" +
                                "<td>" + c.getName() +"</td>" +
                                "<td>" + c.getAddress() +"</td>" +
                                "<td>" + c.getEmail() +"</td>" +
                                "<td>" + c.getTelephone() +"</td>" +
                                showEdit +
                                showDelete +
                            "</tr>"

                );
            }
            out.write(


                    "</tbody>" +
                    "</table>" +
                            "</div>" +
                            showAdd +
                "</body>" +
            "</html>"
            );
            out.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}