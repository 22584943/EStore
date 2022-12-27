import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class BasketDAO {

    public  BasketDAO() {}

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String dbURL = "jdbc:sqlite:everythingStoreDB.sqlite";
            dbConnection = DriverManager.getConnection(dbURL);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


    public boolean addProductToBasket(int productID, int quantity, int currentStock) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;
        String update = "INSERT INTO basket (id, currentStock, quantity) VALUES ("+productID + ","+ currentStock + "," + quantity+") ON CONFLICT(id) DO UPDATE SET quantity=quantity+" + quantity +";";
        boolean ok = false;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(update);
            // execute SQL query
            statement.executeUpdate(update);
            ok = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return ok;
    }

    public ArrayList<Product> getBasket() throws SQLException {
        System.out.println("Retrieving basket ...");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM products INNER JOIN basket ON products.id = basket.id;";
        ArrayList<Product> products = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            //System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {
                int id = result.getInt("id");
                String SKU = result.getString("SKU");
                String category = result.getString("category");
                String name = result.getString("name");
                String description = result.getString("description");
                int price = result.getInt("price");
                int quantity = result.getInt("quantity");
                int stock = result.getInt("stock");
                products.add(new Product(id, quantity, SKU, category, name, description, price, stock));
            }
        } catch(Exception e) {
            System.out.println("get all dvds: "+e);
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return products;
    }

    public boolean emptyBasket() throws SQLException {
        System.out.println("emptying basket...");
        Connection dbConnection = null;
        PreparedStatement st = null;
        String query = "DELETE FROM basket;";

        boolean ok = false;
        try {
            dbConnection = getDBConnection();
           st = dbConnection.prepareStatement("DELETE FROM basket;");

            st.executeUpdate();
            ok = true;
        } catch(Exception e) {
            System.out.println("get all dvds: "+e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return ok;
    }

    public ResponseObject checkoutBasket(ArrayList<Product> basket) throws SQLException {
        System.out.println("checking out basket...");
        Connection dbConnection = null;
        PreparedStatement st = null;
        String query = "";



        boolean ok = false;

        StringBuilder errorMessage = new StringBuilder();
        try {
            for (Product product : basket) {
                System.out.println(product.getStock());
                System.out.println(product.getQuantityOrdered());
                int updatedStock = product.getStock() - product.getQuantityOrdered();
                if (updatedStock <= 0) {
                    errorMessage.append(product.getID()).append(", ").append("Out of Stock").append(";");

                } else {
                    query = "UPDATE products SET stock=" + updatedStock + " WHERE id=" + product.getID() + ";";
                    dbConnection = getDBConnection();
                    System.out.println(query);
                    st = dbConnection.prepareStatement(query);

                    st.executeUpdate();
                    ok = true;
                }
                // TODO - if something goes wrong, break
            }

        } catch(Exception e) {
            System.out.println("get all dvds: "+e);
        } finally {
            if (st != null) {
                st.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        emptyBasket();
        // return response object with success and error message
        ResponseObject responseObject = new ResponseObject(ok, errorMessage.toString());
        return responseObject;
    }
}