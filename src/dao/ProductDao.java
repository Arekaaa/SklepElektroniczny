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
    private String typSortowania=null;
    private int metodaSortowania=0;
    private float kwota=0;
    private boolean nieZnaleziono=false;
    //private String searchSortQuery=null;
    private String typWyszukiwania=null;
    private String wprowadzonaWartosc=null;
    private String descOrAsc=null;

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
            if(resultSet.next()) {
                ilosc = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Błąd bazy danych ! " + ex);
        }
    }
    private void sumujWartosc(){
        String sumujKwote ="SELECT SUM(cena*ilosc) AS wartosc FROM produkty";

        try {
            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sumujKwote);
            if(resultSet.next()) {
                kwota = resultSet.getFloat(1);
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
        sumujWartosc();
        sprawdzIlosc();

        String showDefaultProducts = "SELECT * FROM produkty";
        try {
            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(showDefaultProducts);
            if(!resultSet.next()){
                nieZnaleziono=true;
            }
            else {
                do{
                    ProductBean produkt = new ProductBean();
                    produkt.setId(resultSet.getInt("ID"));
                    produkt.setNazwa(resultSet.getString("Nazwa"));
                    produkt.setProducent(resultSet.getString("Producent"));
                    produkt.setCena(resultSet.getFloat("Cena"));
                    produkt.setIlosc(resultSet.getInt("Ilosc"));
                    listaProduktow.add(produkt);
                }while (resultSet.next());
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
         boolean istnieje = false;
        String nazwa = product.getNazwa();
        String producent = product.getProducent();
        float cena = product.getCena();
        int ilosc = product.getIlosc();

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
                while (resultSet.next()) {
                    if (resultSet.getString("Nazwa").compareTo(nazwa) == 0 && resultSet.getString("Producent").compareTo(producent) == 0) {
                        istnieje = true;
                    }
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (!istnieje) {
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

    public List<ProductBean> showProductByID(ProductBean produkt) throws SQLException {
        List<ProductBean> produktById = new ArrayList<ProductBean>();
        int id = produkt.getId();

        String searchProductQuery = "SELECT * FROM produkty WHERE ID ='"+id+"'";
        try {
            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchProductQuery);
            if(resultSet.next()){{
                    produkt.setId(resultSet.getInt("ID"));
                    produkt.setNazwa(resultSet.getString("Nazwa"));
                    produkt.setProducent(resultSet.getString("Producent"));
                    produkt.setCena(resultSet.getFloat("Cena"));
                    produkt.setIlosc(resultSet.getInt("Ilosc"));
                    produktById.add(produkt);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Błąd bazy danychu ! " + ex);
        }
        return produktById;
    }

        public void editProduct(ProductBean product){
            int id = product.getId();
            String nazwa = product.getNazwa();
            String producent = product.getProducent();
            float cena = product.getCena();
            int ilosc = product.getIlosc();

                String updateProduct = "UPDATE produkty SET Nazwa = '"+nazwa+ "',"+ "Producent = '"+producent+ "',"+"Cena ='"+cena+"',"+"Ilosc ='"+ilosc+"'"+"WHERE ID ='"+id+"'";
                try {
                    connection = ConnectionManager.connectionOthers();
                    if (connection == null) {
                        throw new RuntimeException("Brak połączenia");
                    }
                    statement = connection.createStatement();
                    statement.executeUpdate(updateProduct);
                    product.setDodany(true);
                    statement.close();
                    connection.close();
                    showProducts();

                } catch (SQLException ex) {
                    System.out.println("Błąd bazy danych ! " + ex);
                }
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
        public List<ProductBean> searchProduct(ProductDao product) throws SQLException{
            List<ProductBean> listaProduktowWyszukana = new ArrayList<ProductBean>();
            LoginDao.preparingDB();
            preparingTableProducts();
            sumujWartosc();
            sprawdzIlosc();
            String searchProductQuery = "SELECT * FROM produkty WHERE "+typWyszukiwania+" LIKE '"+wprowadzonaWartosc+"%"+"'";

            try {
                connection = ConnectionManager.connectionOthers();
                if (connection == null) {
                    throw new RuntimeException("Brak połączenia");
                }
                statement = connection.createStatement();
                resultSet = statement.executeQuery(searchProductQuery);
                if(!resultSet.next()){
                    nieZnaleziono=true;
                }
                else {
                    do{     // pętla do while zastosowa aby pobrac wszystkie wiersze z tabeli
                        ProductBean produkt = new ProductBean();
                        produkt.setId(resultSet.getInt("ID"));
                        produkt.setNazwa(resultSet.getString("Nazwa"));
                        produkt.setProducent(resultSet.getString("Producent"));
                        produkt.setCena(resultSet.getFloat("Cena"));
                        produkt.setIlosc(resultSet.getInt("Ilosc"));
                        listaProduktowWyszukana.add(produkt);
                    }while (resultSet.next());
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Błąd bazy danych ! " + ex);
            }
            return listaProduktowWyszukana;
        }

        public List<ProductBean> sortProductMethod(ProductDao product) throws SQLException{
        List<ProductBean> listaProduktowSortowana = new ArrayList<ProductBean>();
        sumujWartosc();
        sprawdzIlosc();
            String searchSortQuery;
            if(metodaSortowania==1){
                searchSortQuery = "SELECT * FROM produkty WHERE " + typWyszukiwania + " LIKE '" +wprowadzonaWartosc+ "%'"+ "ORDER BY " +typSortowania +" "+descOrAsc;
            }
           else {
                searchSortQuery = "SELECT * FROM produkty ORDER BY "+typSortowania +" "+descOrAsc;
            }
        try {
            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchSortQuery);
            if(!resultSet.next()){
                nieZnaleziono=true;
            }
            else {
                do{     // pętla do while zastosowa aby pobrac wszystkie wiersze z tabeli
                    ProductBean produkt = new ProductBean();
                    produkt.setId(resultSet.getInt("ID"));
                    produkt.setNazwa(resultSet.getString("Nazwa"));
                    produkt.setProducent(resultSet.getString("Producent"));
                    produkt.setCena(resultSet.getFloat("Cena"));
                    produkt.setIlosc(resultSet.getInt("Ilosc"));
                    listaProduktowSortowana.add(produkt);
                }while (resultSet.next());
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Błąd bazy danych ! " + ex);
        }
        return listaProduktowSortowana;
    }




    //Gettery/settery klasy ProductDao
    public int getIlosc() {
        return ilosc;
    }

    public float getKwota() {
        return kwota;
    }
    public boolean isNieZnaleziono() {
        return nieZnaleziono;
    }

    public void setTypSortowania(String typSortowania) {
        this.typSortowania = typSortowania;
    }

    public void setDescOrAsc(String descOrAsc) {
        this.descOrAsc = descOrAsc;
    }

    public void setTypWyszukiwania(String typWyszukiwaniaNazwa) {
        this.typWyszukiwania = typWyszukiwaniaNazwa;
    }

    public void setWprowadzonaWartosc(String wprowadzonaWartosc) {
        this.wprowadzonaWartosc = wprowadzonaWartosc;
    }

    public void setMetodaSortowania(int metodaSortowania) {
        this.metodaSortowania = metodaSortowania;
    }
}



