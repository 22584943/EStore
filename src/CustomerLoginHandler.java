import java.io.*;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class CustomerLoginHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));



        out.write(
                getHeader.get() +


                               "<div class=\"login-wrapper\">" +
                        "<h2>Customer Login</h2>" +
                                        "<ul class=\"nav nav-tabs\" id=\"myTab\" role=\"tablist\">\n" +
                        "            <li class=\"nav-item\" role=\"presentation\">\n" +
                        "                <button class=\"nav-link active\" id=\"login-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#login\" type=\"button\" role=\"tab\" aria-controls=\"login\" aria-selected=\"true\">Login</button>\n" +
                        "            </li>\n" +
                        "            <li class=\"nav-item\" role=\"presentation\">\n" +
                        "                <button class=\"nav-link\" id=\"register-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#register\" type=\"button\" role=\"tab\" aria-controls=\"register\" aria-selected=\"true\">Register</button>\n" +
                        "            </li>\n" +
                        "\n" +
                        "        </ul>\n" +
                        "        <div class=\"tab-content\" id=\"myTabContent\">\n" +
                        "            <div class=\"tab-pane fade show active\" id=\"login\" role=\"tabpanel\" aria-labelledby=\"login-tab\">\n" +
                        "                <form action=\"/customer-login-handler\" method=\"post\">\n" +
                        "                    <div class=\"form-row\">\n" +
                        "                        <label for=\"login-email\">Email</label>\n" +
                        "                        <input id=\"login-email\" name=\"email\" type=\"text\" />\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form-row\">\n" +
                        "                        <label for=\"login-password\">Password</label>\n" +
                        "                        <input id=\"login-password\" name=\"password\" type=\"password\" />\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form-row\">\n" +
                        "                        <button type=\"submit\">Login</button>\n" +
                        "                    </div>\n" +
                        "                </form>\n" +
                        "            </div>\n" +
                        "            <div class=\"tab-pane fade\" id=\"register\" role=\"register\" aria-labelledby=\"register-tab\">\n" +
                        "                    <form action=\"/customer-register-handler\" method=\"post\">\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-email\">Email</label>\n" +
                        "                            <input id=\"reg-email\" name=\"email\"type=\"email\" />\n" +
                        "                        </div>\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-name\">Name</label>\n" +
                        "                            <input id=\"reg-name\" name=\"name\"type=\"text\" />\n" +
                        "                        </div>\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-telephone\">Telephone</label>\n" +
                        "                            <input id=\"reg-telephone\" name=\"telephone\"type=\"text\" />\n" +
                        "                        </div>\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-house-num\">House number/name</label>\n" +
                        "                            <input id=\"reg-house-num\" name=\"houseNum\"type=\"text\" />\n" +
                        "                        </div>\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-first-line\">First Line of Address</label>\n" +
                        "                            <input id=\"reg-first-line\" name=\"addressFirstLine\"type=\"text\" />\n" +
                        "                        </div>\n"  +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-town\">Town</label>\n" +
                        "                            <input id=\"reg-town\" name=\"town\"type=\"text\" />\n" +
                        "                        </div>\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-county-state\">County/State</label>\n" +
                        "                            <input id=\"reg-county-state\" name=\"countyState\"type=\"text\" />\n" +
                        "                        </div>\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-postcode\">Postcode</label>\n" +
                        "                            <input id=\"reg-postcode\" name=\"postcode\"type=\"text\" />\n" +
                        "                        </div>\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-country\">Country</label>\n" +
                        "                            <input id=\"reg-country\" name=\"country\"type=\"text\" />\n" +
                        "                        </div>\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-password\">Password</label>\n" +
                        "                            <input id=\"reg-password\" name=\"password\"type=\"password\" />\n" +
                        "                        </div>\n" +
                                                "<div class=\"form-row\">" +
                                                "  <label for=\"reg-confirm-password\">Confirm Password</label>" +
                                                "  <input id=\"reg-confirm-password\" type=\"password\" />" +
                                                "</div>"+
                        "                        <div class=\"form-row\">\n" +
                        "                            <button type=\"submit\">Register</button>\n" +
                        "                        </div>\n" +
                        "                    </form>\n" +
                        "            </div>\n" +
                        "\n" +
                        "        </div>"+
                                "</div>" +
                        "    </body>" +
                        "</html>"
        );
        out.close();




    }

}