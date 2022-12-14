import java.io.*;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LoginHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));



        out.write(
                getHeader.get() +


                               "<div class=\"form-wrapper\">" +
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
                        "                <form action=\"/login-form-handler\" type=\"get\">\n" +
                        "                    <div class=\"form-row\">\n" +
                        "                        <label for=\"login-username\">Username</label>\n" +
                        "                        <input id=\"login-username\" name=\"username\" type=\"text\" />\n" +
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
                        "                    <form action=\"/register-form-handler\" type=\"get\">\n" +
                        "                        <div class=\"form-row\">\n" +
                        "                            <label for=\"reg-username\">Username</label>\n" +
                        "                            <input id=\"reg-username\" name=\"username\"type=\"text\" />\n" +
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