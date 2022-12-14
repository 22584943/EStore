import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

class Main {
    static final private int PORT = 8005;

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
        server.createContext("/", new RootHandler() );
        server.createContext("/customers", new CustomerHandler() );
        server.createContext("/products", new ProductHandler() );
        server.createContext("/login", new LoginHandler() );
        server.createContext("/register-form-handler", new RegisterFormHandler() );
        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);
    }




}
