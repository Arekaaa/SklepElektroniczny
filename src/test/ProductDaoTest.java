package test;

import beans.ProductBean;
import dao.ConnectionManager;
import dao.LoginDao;
import dao.ProductDao;
import org.junit.jupiter.api.Test;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    @Test
    void connectJDBCTest(){
        Connection connection=null;
        try {
            connection = ConnectionManager.connectionToCreateDB();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
        assertNotNull(connection); // Sprawdza czy połączenie z bazą danych nie jest nullem. Jeśli jest to błąd
    }
    @Test
    void preparingTableProducts() {
        Connection connection = null;
        Statement statement = null;
        int sprawdzPoprawnosc=0;

        try {
            String createTableUsers = "CREATE TABLE IF NOT EXISTS " +
                    "produkty (ID int(10) NOT NULL AUTO_INCREMENT, Nazwa varchar(30) NOT NULL, Producent varchar(30) NOT NULL, " +
                    "Cena float(10) NOT NULL, Ilosc int(10) NOT NULL, Primary Key(ID))";
            connection = ConnectionManager.connectionOthers();
            statement = connection.createStatement();
            statement.executeUpdate(createTableUsers);
            sprawdzPoprawnosc=1;

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
        assertNotNull(connection);
        assertEquals(1,sprawdzPoprawnosc);
    }
    @Test
    void sprawdzIlosc(){
        float wartosc=-1;
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet= null;
        try {
            String query = "SELECT SUM(ilosc) AS podliczenie FROM produkty";
            connection = ConnectionManager.connectionOthers();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                wartosc = resultSet.getFloat("podliczenie");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* */}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
        assertNotNull(connection);
        assertTrue(wartosc>=0); // Sprawdza czy ilość danego produktu jest większa od 0
    }
    @Test
    void sumujWartosc(){
        float wartosc=-1;
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet= null;
        try {
            String query = "SELECT SUM(cena*ilosc) AS wartosc FROM produkty";
            connection = ConnectionManager.connectionOthers();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                wartosc = resultSet.getFloat("wartosc");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* */}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
        assertNotNull(connection);       // Sprawdza czy istnieje połaczenie
        assertTrue(wartosc>=0);
    }
    @Test
    void insertProduct(){
        LoginDao.preparingDB();
        ProductDao.preparingTableProducts();
        Connection connection=null;
        Statement statement=null;
        int sprawdzPoprawnosc=0;
        try {
            String insertProduct = "INSERT INTO produkty VALUES (-2, 'Testowy Produkt', 'Testowy Producent',9999,99)"; // Testowe dodanie produktu o numerze ID = -2 następnie automatyczne usunięcie go
            connection = ConnectionManager.connectionOthers();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            sprawdzPoprawnosc = statement.executeUpdate(insertProduct);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* */}
            }
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
        assertNotNull(connection);
        assertEquals(1,sprawdzPoprawnosc);
    }


    @Test
    void deleteProduct() {
        ProductBean product = new ProductBean();
        Connection connection=null;
        Statement statement=null;
        product.setId(99999); // Na potrzeby testu przyjęto numer ID jako 99999
        Integer id = product.getId();
        try {
            String deleteProduct = "DELETE FROM produkty WHERE ID ='"+id+"'";

            connection = ConnectionManager.connectionOthers();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(deleteProduct);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Wyjątek związany z błędną składnią SQL");
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* */}
            }
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
        assertNotNull(connection);
        assertNotNull(id); // Sprawdza czy ID po których usuwa się produkty z bazy danych nie jest nullem. Jeśli jest to błąd
    }

    @Test
    void showProducts(int poczatkowyProdukt, int rekordyNaStrone) {
        List<ProductBean> listaTestowa = new ArrayList<ProductBean>();
        LoginDao.preparingDB();
        preparingTableProducts();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        ProductBean produktTestowy = new ProductBean();
        int sprawdzPoprawnosc=0;
        try {
            String showDefaultProducts = "SELECT * FROM produkty LIMIT " + poczatkowyProdukt+ ", " + rekordyNaStrone;

            connection = ConnectionManager.connectionOthers();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(showDefaultProducts);
            if(!resultSet.next()){
                sprawdzPoprawnosc=1;
            }
            else {
                do{
                    produktTestowy.setId(resultSet.getInt("ID"));
                    produktTestowy.setNazwa(resultSet.getString("Nazwa"));
                    produktTestowy.setProducent(resultSet.getString("Producent"));
                    produktTestowy.setCena(resultSet.getFloat("Cena"));
                    produktTestowy.setIlosc(resultSet.getInt("Ilosc"));
                    listaTestowa.add(produktTestowy);
                }while (resultSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Wyjątek związany z błędną składnią SQL");
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* */}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* */}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
        assertNotNull(connection);
        assertFalse(listaTestowa.isEmpty()); // sprawdza czy lista nie jest pusta. Jeśli by była to test failed
        //assertTrue(sprawdzPoprawnosc==1 || sprawdzPoprawnosc==2);
    }
}