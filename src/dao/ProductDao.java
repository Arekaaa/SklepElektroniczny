package dao;

import beans.ProductBean;
import controller.LoginController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDao {
    static Connection connection = null;
    static ResultSet resultSet = null;
    static Statement statement = null;

    public static void preparingTableProducts() {


        String createTableUsers = "CREATE TABLE IF NOT EXISTS " +
                "produkty (ID int(10) NOT NULL AUTO_INCREMENT, Nazwa varchar(30) NOT NULL, Producent varchar(30) NOT NULL, " +
                "Cena float(10) NOT NULL, Ilosc int(10) NOT NULL, Primary Key(ID))";
        try {
            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            statement.executeUpdate(createTableUsers);
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public List<ProductBean> showProducts() throws SQLException {
        List<ProductBean> listaProduktow = new ArrayList<ProductBean>();
        LoginDao.preparingDB();
        preparingTableProducts();

        String searchProductQuery = "SELECT * FROM produkty";
        try {
            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchProductQuery);
            while (resultSet.next()) {
                ProductBean produkt = new ProductBean();
                produkt.setId(resultSet.getInt("ID"));
                produkt.setNazwa(resultSet.getString("Nazwa"));
                produkt.setProducent(resultSet.getString("Producent"));
                produkt.setCena(resultSet.getFloat("Cena"));
                produkt.setIlosc(resultSet.getInt("Ilosc"));
                listaProduktow.add(produkt);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Błąd przy logowaniu ! " + ex);
        }
        return listaProduktow;
    }

    public void addProduct(ProductBean product) {

        String nazwa = product.getNazwa();
        String producent = product.getProducent();
        float cena = product.getCena();
        int ilosc = product.getIlosc();
        boolean nieIstnieje = true;


            LoginDao.preparingDB();
            preparingTableProducts();
            try {
                String searchQuery =
                        "SELECT * FROM produkty WHERE Nazwa='"
                                + nazwa
                                + "' OR Producent='"
                                + producent
                                + "'";

                connection = ConnectionManager.connectionOthers();
                if (connection == null) {
                    throw new RuntimeException("Brak połączenia");
                }
                statement = connection.createStatement();
                statement.executeQuery(searchQuery);
                resultSet = statement.executeQuery(searchQuery);
                if (resultSet.next()) {
                    if (resultSet.getString("Nazwa").compareTo(nazwa) == 0 && resultSet.getString("Producent").compareTo(producent) == 0) {
                        nieIstnieje = false;
                    }
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (nieIstnieje) {
                String insertProduct = "INSERT INTO produkty VALUES (NULL,'" + nazwa + "','" + producent + "','" + cena + "','" + ilosc + "')";
                try {
                    connection = ConnectionManager.connectionOthers();
                    if (connection == null) {
                        throw new RuntimeException("Brak połączenia");
                    }
                    statement = connection.createStatement();
                    statement.executeUpdate(insertProduct);
                    product.setDodany(true);
                    statement.close();
                    connection.close();
                    showProducts();

                } catch (SQLException ex) {
                    System.out.println("Błąd przy rejestracji ! " + ex);
                }
            }

        }
    }



