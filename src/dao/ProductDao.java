package dao;

import beans.ProductBean;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static Connection connection = null;
    private static ResultSet resultSet = null;
    private static Statement statement = null;
    private int ilosc=0;


    private static void preparingTableProducts() {

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

    private void sprawdzIlosc(){
        String sumujProdukty ="SELECT SUM(ilosc) AS podliczenie FROM produkty";

        try {
            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sumujProdukty);
            while(resultSet.next()) {
                ilosc = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Błąd bazy danych ! " + ex);
        }
    }
    public List<ProductBean> showProducts() throws SQLException {
        List<ProductBean> listaProduktow = new ArrayList<ProductBean>();
        LoginDao.preparingDB();
        preparingTableProducts();
        sprawdzIlosc();

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
            System.out.println("Błąd bazy danychu ! " + ex);
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
                    System.out.println("Błąd bazy danych ! " + ex);
                }
            }

        }

        public List<ProductBean>  searchProduct(ProductBean product) throws SQLException{
            List<ProductBean> listaProduktowWyszukana = new ArrayList<ProductBean>();

            String typWyszukiwania = product.getTypWyszukiwania();
            String wartosc = product.getWprowadzonaWartosc();
            LoginDao.preparingDB();
            preparingTableProducts();
            sprawdzIlosc();

            String searchProductQuery = "SELECT * FROM produkty WHERE "+typWyszukiwania+" LIKE '"+wartosc+"%"+"' OR "+typWyszukiwania+" LIKE '"+wartosc+"%"+"'";

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
                    listaProduktowWyszukana.add(produkt);
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Błąd bazy danych ! " + ex);
            }
            return listaProduktowWyszukana;
        }

        public void deleteProduct(ProductBean product) throws SQLException {
            int id = product.getId();
            String deleteProduct = "DELETE FROM produkty WHERE ID ='"+id+"'";
            try {
                connection = ConnectionManager.connectionOthers();
                if (connection == null) {
                    throw new RuntimeException("Brak połączenia");
                }
                statement = connection.createStatement();
                statement.executeUpdate(deleteProduct);
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Błąd bazy danych ! " + ex);
            }
        }

    public int getIlosc() {
        return ilosc;
    }
}



