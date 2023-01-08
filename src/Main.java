import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.sql.SQLException;

class Main {
    static final private int PORT = 8005;

    public static void main(String[] args) throws IOException, SQLException {


        // start web server
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
        server.createContext("/", new RootHandler() );
        server.createContext("/customers", new CustomerHandler() );
        server.createContext("/customers/edit", new EditCustomerHandler());
        server.createContext("/customers/edit-customer-handler", new EditCustomerFormHandler() );
        server.createContext("/customers/add", new AddCustomerHandler() );
        server.createContext("/customers/add-customer-handler", new AddCustomerFormHandler() );
        server.createContext("/customers/delete", new DeleteCustomerHandler() );
        server.createContext("/customers/delete-customer-handler", new DeleteCustomerFormHandler() );
        server.createContext("/admin", new AdminLoginHandler() );
        server.createContext("/product", new ShowProductHandler() );
        server.createContext("/products", new ProductHandler() );
        server.createContext("/products/delete", new DeleteProductHandler() );
        server.createContext("/products/delete-product-handler", new DeleteProductFormHandler() );
        server.createContext("/products/add", new AddProductHandler() );
        server.createContext("/products/add-product-handler", new AddProductFormHandler() );
        server.createContext("/products/edit", new EditProductHandler() );
        server.createContext("/products/edit-product-handler", new EditProductFormHandler() );
        server.createContext("/products/product-search", new ProductSearchResultsHandler() );
        server.createContext("/basket", new BasketHandler() );
        server.createContext("/basket/add", new AddBasketHandler() );
        server.createContext("/basket/remove", new DeleteBasketItemHandler() );
        server.createContext("/basket/empty", new EmptyBasketHandler() );
        server.createContext("/basket/checkout", new CheckoutBasket() );
        server.createContext("/login", new CustomerLoginHandler() );
        server.createContext("/logout", new LogoutHandler() );
        server.createContext("/admin-register-handler", new AdminRegisterFormHandler() );
        server.createContext("/customer-register-handler", new CustomerRegisterFormHandler() );
        server.createContext("/admin-login-handler", new AdminLoginFormHandler() );
        server.createContext("/customer-login-handler", new CustomerLoginFormHandler() );

        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);

        // start command line
//        Controller c = new Controller();
        Controller.run();
    }




}
