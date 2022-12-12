public class getHeader {
    static public String get() {
        return "<head>" +
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css\"/>" +
//                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">" +
                "        <style>" +
                "            * {font-family: Futura, \"Trebuchet MS\", Arial, sans-serif;" +
                "                padding: 0;" +
                "                margin: 0;" +
                "                text-decoration: none;" +
                " box-sizing: border-box;" +
                "            }" +
                "            a {" +
                "                color: inherit;" +
                "            }" +
                "            header h1 {" +
                "                color: white;" +
                "            }" +
                " .table-cont {" +
                "   width: 100%;" +
                "height: 100%;" +
                "display: flex;" +
                "justify-content:center;" +
                "align-items: center;}" +
                "            header {" +
                "                background: #000;" +
                "                display: flex;" +
                "                padding: 0.75em 3%;" +
                "                justify-content: space-between;" +
                "                align-items: center;" +
                "                color: white;" +
                "            }" +
                "            header i {" +
                "                padding: 0.15em 0.5em;" +
                "                font-size: 1.5em;" +
                "" +
                "            }" +
                "            header h1 {" +
                "                line-height: 1.5em;" +
                "            }" +
                "            .logo-cont, i, nav > ul {" +
                "                display: flex;" +
                "                justify-content: center;" +
                "                align-items: center;" +
                "            }" +
                "            nav ul {" +
                "                list-style-type: none;" +
                "                padding: 0;" +
                "                font-size: 1.25em;" +

                "            }" +

                "            nav ul li {" +
                "                padding: 0.5em;" +
                "            }" +
                "            nav ul li:hover {" +
                "                cursor: pointer;" +
                "            }" +
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
                "                    <li>Login</li>" +
                "                </ul>" +
                "            </nav>" +
                "        </header>";
    }
}
