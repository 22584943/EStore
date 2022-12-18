public class getHeader {
    static public String get() {
        UserDAO ud = new UserDAO();
        // Check if logged in
        boolean isLoggedIn = ud.isLoggedIn();
        // Condition button render
        String showLoginLink = isLoggedIn ? "<a href=\"\\logout\">Logout</a>" : "<a href=\"\\login\">Login</a>";

        return "<head>" +
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css\"/>" +
                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>" +
                "        <style>" +
                " * {\n" +
                "    font-family: Futura, \"Trebuchet MS\", Arial, sans-serif;\n" +
                "    padding: 0;\n" +
                "    margin: 0;\n" +
                "    text-decoration: none!important;\n" +
                "    box-sizing: border-box!important;\n" +
                "}\n" +
                "a {\n" +
                "   color: inherit!important;\n" +
                "}" +
                "header h1 {\n" +
                "   color: white;\n" +
                "   margin: 0!important;" +
                "}\n" +
                "\n" +
                ".table-cont, .form-wrapper, .flex-center {\n" +
                "    width: 100%;\n" +
                "    height: 100%;\n" +
                "    display: flex;\n" +
                "    justify-content:center;\n" +
                "    align-items: center;\n" +
                "}\n" +
                ".flex-down {\n" +
                "    flex-direction: column;\n" +
                "}" +
                "header {\n" +
                "   background: #000;\n" +
                "   display: flex;\n" +
                "   padding: 0.75em 3%;\n" +
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
                "}" +
                ".form-wrapper {\n" +
                "    flex-direction: column;\n" +
                "    justify-content: flex-start;" +
                "    padding-top: 200px;" +
                "}" +
                "form {\n" +
                "    display: flex;\n" +
                "    flex-direction: column;\n" +
                "    justify-content: center;\n" +
                "    align-items: center;\n" +
                "    min-width: 250px; \n" +
                "    padding-top: 2em;" +
                "}\n" +
                "form button[type=\"submit\"] {\n" +
                "    width: 100%;\n" +
                "    background: navy;\n" +
                "    color: white;\n" +
                "    text-transform: uppercase;\n" +
                "    border: none;"+
                "}" +
                "form input, button[type=\"submit\"] {\n" +
                "   width: 100%;" +
                "    padding: 0.5em;\n" +
                "    margin-bottom: 1em;\n" +
                "}" +
                "form .form-row {\n" +
                "    width: 100%;" +
                "    display: flex;\n" +
                "    flex-direction: column;\n" +
                "    align-items: flex-start;\n" +
                "}"+
                ".edit-product-form {\n" +
                "    width: 70%;\n" +
                "    margin: auto;\n" +
                "    display: flex;\n" +
                "    flex-direction: column;\n" +
                "}" +
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
                "                    <li><a href=\"/customers\">Customers</a></li>" +
                "                    <li>" +
                        showLoginLink +
                "</li>" +
                "                </ul>" +
                "            </nav>" +
                "        </header>";
    }
}
