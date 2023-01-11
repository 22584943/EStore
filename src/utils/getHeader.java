package utils;

import DAOs.UserDAO;

public class getHeader {
    static public String get() {
        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isAdminLoggedIn = ud.isLoggedIn("Admin");
        boolean isCustomerLoggedIn = ud.isLoggedIn("Customer");
        // Condition button render
        String showAdminLink = !isAdminLoggedIn ? "<li><a href=\"/admin\">Admin</a></li>" : "";
        String showLoginLink = isAdminLoggedIn || isCustomerLoggedIn ? "<a href=\"/logout\">Logout</a>" : "<a href=\"/login\">Customer Login</a>";
        String showCustomersLink = isAdminLoggedIn ? "<li><a href=\"/customers\">Customers</a></li>" : "";
        return "<head>" +
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css\"/>" +
                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>" +
                "        <style>" +
                " html, body {\n" +
                "    font-family: Futura, \\\"Trebuchet MS\\\", Arial, sans-serif;\n" +
                "    padding: 0!important;\n" +
                "    margin: 0!important;\n" +
                "    box-sizing: border-box!important;\n" +
                "}\n" +
                "a {\n" +
                "   color: inherit!important;\n" +
                "   text-decoration: none!important;\n" +
                "}\n" +
                "\n" +
                "/* Custom scrollbar */\n" +
                "\n" +
                "/* width */\n" +
                "::-webkit-scrollbar {\n" +
                "  width: 10px;\n" +
                "}\n" +
                "\n" +
                "/* Track */\n" +
                "::-webkit-scrollbar-track {\n" +
                "  background: #f1f1f1;\n" +
                "}\n" +
                "\n" +
                "/* Handle */\n" +
                "::-webkit-scrollbar-thumb {\n" +
                "  background: #888;\n" +
                "}\n" +
                "\n" +
                "/* Handle on hover */\n" +
                "::-webkit-scrollbar-thumb:hover {\n" +
                "  background: #555;\n" +
                "}\n" +
                "header h1 {\n" +
                "   color: white;\n" +
                "   margin: 0!important;\n" +
                "}\n" +
                "\n" +
                ".login-wrapper, .flex-center{\n" +
                "    width: 70%;\n" +
                "    margin: auto;\n" +
                "    height: 100%;\n" +
                "    display: flex;\n" +
                "    justify-content:center;\n" +
                "    align-items: center;\n" +
                "}\n" +
                "\n" +
                ".table-cont {\n" +
                "justify-content: flex-start;\n" +
                "width: 80%;\n" +
                "margin: auto!important;\n" +
                "border-radius: 10px;\n" +
                "overflow: hidden;\n" +
                "padding: 1em;\n" +
                "}\n" +
                "\n" +
                "body {\n" +
                "padding: 4em 0;}\n" +
                ".category-btn-cont {\n" +
                "display: flex;\n" +
                "margin: auto;\n" +
                "justify-content: center;\n" +
                "align-items: center;\n" +
                "}\n" +
                "\n" +
                "\n" +
                ".category-btn-cont a {\n" +
                "    padding: 0.25em 1em;\n" +
                "    background: #0000ff;\n" +
                "    color: white!important;\n" +
                "    border: 1px white solid;\n" +
                "    border-radius: 10px;\n" +
                "    margin: 2em 1em;\n" +
                "}\n" +
                "\n" +
                "h2, h3 {\n" +
                "    width: 70%;\n" +
                "    margin: 0 auto!important;\n" +
                "    text-align: center;\n" +
                "}\n" +
                "\n" +
                "h3 {\n" +
                "    font-size: 0.9em;\n" +
                "}\n" +
                "\n" +
                ".flex {\n" +
                "display: flex;}\n" +
                ".flex-down {\n" +
                "    flex-direction: column!important;\n" +
                "    align-items: flex-start!important;\n" +
                "}\n" +
                "header {\n" +
                "   background: #000;\n" +
                "   display: flex;\n" +
                "   padding: 0.75em 3%!important;\n" +
                "   justify-content: space-between;\n" +
                "   align-items: center;\n" +
                "   color: white;\n" +
                "}\n" +
                "header i {\n" +
                "   padding: 0.15em 0.5em;\n" +
                "   font-size: 1.5em;\n" +
                "\n" +
                "}\n" +
                "header h1 {\n" +
                "   line-height: 1.5em;\n" +
                "}\n" +
                ".logo-cont, i, nav > ul {\n" +
                "   display: flex;\n" +
                "   justify-content: center;\n" +
                "   align-items: center;\n" +
                "}\n" +
                "nav ul {\n" +
                "   list-style-type: none;\n" +
                "   padding: 0;\n" +
                "   margin: 0;\n" +
                "   font-size: 1.25em;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "nav ul li {\n" +
                "   padding: 0.5em;\n" +
                "}\n" +
                "nav ul li:hover {\n" +
                "   cursor: pointer;\n" +
                "}\n" +
                ".login-icon {\n" +
                "font-size: 4em;\n" +
                "padding-bottom: 0.25em;\n" +
                "}\n" +
                "\n" +
                ".login-wrapper h2 {\n" +
                "padding-bottom: 0.25em;\n" +
                "}" +
                "\n" +
                ".basket-wrapper {\n" +
                "display: flex;\n" +
                "width: 100%;\n" +
                "height: 100%;\n" +
                "flex-direction: column;\n" +
                "justify-content: center;\n" +
                "align-items: center;}\n" +
                "\n" +
                ".wrapper {\n" +
                "    padding: 4em 0;\n" +
                "}\n" +
                "\n" +
                ".login-wrapper {\n" +
                "    flex-direction: column;\n" +
                "    justify-content: flex-start;\n" +
                "    padding-top: 100px;\n" +
                "}\n" +
                "\n" +
                ".login-wrapper form {\n" +
                "    display: flex;\n" +
                "    flex-direction: column;\n" +
                "    justify-content: center;\n" +
                "    align-items: center;\n" +
                "    min-width: 250px;\n" +
                "    padding-top: 2em;\n" +
                "}\n" +
                "\n" +
                ".login-wrapper input, .login-wrapper button[type=\"submit\"] {\n" +
                "    padding: 0.5em;\n" +
                "    margin-bottom: 1em;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                "form button[type=\"submit\"] {\n" +
                "    width: 100%;\n" +
                "    background: navy;\n" +
                "    color: white;\n" +
                "    text-transform: uppercase;\n" +
                "    border: none;\n" +
                "}\n" +
                "form[action=\"/customer-register-handler\"] .form-row {\n" +
                "flex-direction: row;\n" +
                "justify-content: space-between;\n" +
                "align-items: center;\n" +
                "}\n" +
                "\n" +
                "form[action=\"/customer-register-handler\"] input {\n" +
                "width: 100%;\n" +
                "}\n" +
                "\n" +
                ".form-item {\n" +
                "width: 48%;\n" +
                "display: flex;\n" +
                "flex-direction: column;\n" +
                "align-items: flex-start;\n" +
                "}" +
                ".tr {\n" +
                "display: flex;\n" +
                "justify-content: flex-start;\n" +
                "align-items: center;\n" +
                "padding: 0.25em 0.5em!important;\n" +
                "}\n" +
                "\n" +
                ".tr a {\n" +
                "display: flex;\n" +
                "align-items: center;\n" +
                "font-size: 0.9em;\n" +
                "}\n" +
                ".tr a i {\n" +
                "    padding-right: 0.25em;\n" +
                "    padding-bottom: 0.2em;\n" +
                "}\n" +
                "\n" +
                ".tr a:hover {\n" +
                "color: blue;\n" +
                "}\n" +
                "\n" +
                "form .form-row {\n" +
                "    width: 100%;\n" +
                "    display: flex;\n" +
                "    flex-direction: column;\n" +
                "    align-items: flex-start;\n" +
                "}\n" +
                "\n" +
                ".edit-product-form {\n" +
                "    width: 70%;\n" +
                "    margin: auto;\n" +
                "    display: flex;\n" +
                "    flex-direction: column;\n" +
                "}\n" +
                "\n" +
                "/* search form */\n" +
                ".search-form {\n" +
                "min-width: 200px;\n" +
                "width: 35%;\n" +
                "display: flex;\n" +
                "flex-direction: row;\n" +
                "margin: auto;\n" +
                "\n" +
                "}\n" +
                "\n" +
                ".search-form > * {\n" +
                "height: 2em;\n" +
                "margin: 0;\n" +
                "}\n" +
                "\n" +
                "form[action=\"/basket/add\"] {\n" +
                "    display: flex;\n" +
                "    flex-direction: row;\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "}\n" +
                "\n" +
                ".product-basket-cell {\n" +
                "    width: 200px;\n" +
                "}\n" +
                "\n" +
                ".product-basket-cell form {\n" +
                "width: 100%;}\n" +
                ".product-basket-cell input,\n" +
                ".product-basket-cell button {\n" +
                "    height: 2.5em;\n" +
                "}\n" +
                ".product-basket-cell button {\n" +
                "    width: 30%;\n" +
                "}\n" +
                "/* always show arrows */\n" +
                "input[type=number]::-webkit-inner-spin-button,\n" +
                "input[type=number]::-webkit-outer-spin-button {\n" +
                "\n" +
                "   opacity: 1;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "form input[type=number] {\n" +
                "    width:60px;\n" +
                "}\n" +
                "\n" +
                ".product-basket-cell form button {\n" +
                "width: 50px;\n" +
                "}\n" +
                "\n" +
                ".tr a span {\n" +
                "margin-right: 1em;\n" +
                "}\n" +
                "\n" +
                ".tr .cell-cont {\n" +
                "width: 11%;}\n" +
                "\n" +
                ".tr .cell-cont:nth-of-type(5) {\n" +
                "width: 23%;}" +
                ".edit-btn-cell {\n" +
                "display: flex;\n" +
                "color: #bfbfff;\n" +
                "}" +
                ".tr:nth-of-type(odd) {\n" +
                "    background: #5a5a5a;\n" +
                "    color: white;\n" +
                "}\n" +
                "\n" +
                ".tr:nth-of-type(even) {\n" +
                "    background: #353535;\n" +
                "    color: white;\n" +
                "}\n" +
                "\n" +
                ".tr:nth-of-type(1) {\n" +
                "    background: white;\n" +
                "    color: black;\n" +
                "}\n" +
                ".tr .cell-cont{\n" +
                "    display: flex;\n" +
                "    justify-content: flex-start;\n" +
                "    align-items: center;\n" +
                "}\n" +
                "\n" +
                ".checkout-total-cont {\n" +
                "display: flex;\n" +
                "margin-left: 0;\n" +
                "flex-direction: column;\n" +
                "align-items: flex-end;\n" +
                "font-weight: bold;\n" +
                "}\n" +
                "\n" +
                ".checkout-btn {\n" +
                "background: orange;\n" +
                "color: white!important;\n" +
                "padding: 0.25em 0.5em;\n" +
                "border-radius: 10px;\n" +
                "}" +
                ".dropdown-cont {\n" +
                "position: relative;}\n" +
                "\n" +
                "#dropdown-results {\n" +
                "width: 100%;\n" +
                "background: white;\n" +
                "position: absolute;\n" +
                "list-style-type: none;}\n" +
                "\n" +
                "#dropdown-results li {\n" +
                "padding: 0.5em;}\n" +
                "#dropdown-results li:hover {\n" +
                "background: orange;}" +
                "        </style>" +
                "    </head>" +
                "    <body>" +
                "        <header>" +
                "            <a class=\"logo-cont\" href=\"/\">" +
                "                <i class=\"bi bi-bag-check-fill\"></i>" +
                "                <h1>EverythingStore</h1>" +
                "            </a>" +
                "            <nav>" +
                "                <ul>" +
                "                    <li><a href=\"/products\">Products</a></li>" +
                showCustomersLink +
                showAdminLink +
                "                    <li>" +
                        showLoginLink +
                "</li>" +
                "                    <li><a href=\"/basket\"><i class=\"bi bi-cart-fill\"></i></a></li>" +
                "                </ul>" +
                "            </nav>" +
                "        </header>";
    }
}
