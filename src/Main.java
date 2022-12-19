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
        server.createContext("/products", new ProductHandler() );
        server.createContext("/products/edit", new EditProductHandler() );
        server.createContext("/products/edit-product-handler", new EditProductFormHandler() );
        server.createContext("/login", new LoginHandler() );
        server.createContext("/logout", new LogoutHandler() );
        server.createContext("/register-form-handler", new RegisterFormHandler() );
        server.createContext("/login-form-handler", new LoginFormHandler() );

        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);

        // start command line
//        Controller c = new Controller();
        Controller.run();
    }




}
