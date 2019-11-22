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
    private int liczbaRekordow=0;
    private String typSortowania=null;
    private int metodaSortowania=0;
    private float kwota=0;
    private boolean nieZnaleziono=false;
    private String typWyszukiwania=null;
    private String wprowadzonaWartosc=null;
    private String descOrAsc=null;

    public static void preparingTableProducts() {
        try {
            String createTableUsers = "CREATE TABLE IF NOT EXISTS " +
                    "produkty (ID int(10) NOT NULL AUTO_INCREMENT, Nazwa varchar(30) NOT NULL, Producent varchar(30) NOT NULL, " +
                    "Cena float(10) NOT NULL, Ilosc int(10) NOT NULL, Primary Key(ID))";

            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
            }
            statement = connection.createStatement();
            statement.executeUpdate(createTableUsers);

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
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
    }

    public void sumujRekordy(){
        try {
            String sumujRekordy ="SELECT COUNT(1) FROM produkty";

            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sumujRekordy);
            if(resultSet.next()) {
                liczbaRekordow = resultSet.getInt(1);
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
    }
    public void sumujRekordyWyszukane(){
        try {
            String sumujRekordy ="SELECT COUNT(1) FROM produkty  WHERE "+typWyszukiwania+" LIKE '"+wprowadzonaWartosc+"%"+"'";

            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sumujRekordy);
            if(resultSet.next()) {
                liczbaRekordow = resultSet.getInt(1);
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
    }

    private void sprawdzIlosc(){
        try {
            String sumujProdukty ="SELECT SUM(ilosc) AS podliczenie FROM produkty";

            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sumujProdukty);
            if(resultSet.next()) {
                ilosc = resultSet.getInt(1);
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
    }
    private void sumujWartosc(){
        try {
            String sumujKwote ="SELECT SUM(cena*ilosc) AS wartosc FROM produkty";

            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sumujKwote);
            if(resultSet.next()) {
                kwota = resultSet.getFloat(1);
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
    }

    public List<ProductBean> showProducts(int poczatkowyProdukt, int rekordyNaStrone) {
        List<ProductBean> listaProduktow = new ArrayList<ProductBean>();
        LoginDao.preparingDB();
        preparingTableProducts();
        sumujWartosc();
        sprawdzIlosc();

        try {
            String showDefaultProducts = "SELECT * FROM produkty LIMIT " + poczatkowyProdukt+ ", " + rekordyNaStrone;

            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
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
        return listaProduktow;
    }

    public void addProduct(ProductBean product) {
        LoginDao.preparingDB();
        preparingTableProducts();
        boolean istnieje = false;
        String nazwa = product.getNazwa();
        String producent = product.getProducent();
        float cena = product.getCena();
        int ilosc = product.getIlosc();

            try {
                String searchQuery =
                        "SELECT * FROM produkty WHERE Nazwa='"
                                + nazwa
                                + "' OR Producent='"
                                + producent
                                + "'";

                connection = ConnectionManager.connectionOthers();
                if (connection == null) {
                    throw new RuntimeException("Brak połączenia z bazą danych");
                }
                statement = connection.createStatement();
                statement.executeQuery(searchQuery);
                resultSet = statement.executeQuery(searchQuery);
                while (resultSet.next()) {
                    if (resultSet.getString("Nazwa").compareTo(nazwa) == 0 && resultSet.getString("Producent").compareTo(producent) == 0) {
                        istnieje = true;
                    }
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

            if (!istnieje) {
                try {
                    String insertProduct = "INSERT INTO produkty VALUES (NULL,'" + nazwa + "','" + producent + "','" + cena + "','" + ilosc + "')";

                    connection = ConnectionManager.connectionOthers();
                    if (connection == null) {
                        throw new RuntimeException("Brak połączenia z bazą danych");
                    }
                    statement = connection.createStatement();
                    statement.executeUpdate(insertProduct);
                    product.setDodany(true);
                    //showProducts(liczbaRekordow,10);
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
                            connection.close();
                        } catch (SQLException e) { /* */}
                    }
                }
            }
        }

    public List<ProductBean> showProductByID(ProductBean produkt)  {
        List<ProductBean> produktById = new ArrayList<ProductBean>();
        int id = produkt.getId();

        try {
            String searchProductQuery = "SELECT * FROM produkty WHERE ID ='"+id+"'";

            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
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
        return produktById;
    }

        public void editProduct(ProductBean product){
            int id = product.getId();
            String nazwa = product.getNazwa();
            String producent = product.getProducent();
            float cena = product.getCena();
            int ilosc = product.getIlosc();

                try {
                    String updateProduct = "UPDATE produkty SET Nazwa = '"+nazwa+ "',"+ "Producent = '"+producent+ "',"+"Cena ='"+cena+"',"+"Ilosc ='"+ilosc+"'"+"WHERE ID ='"+id+"'";

                    connection = ConnectionManager.connectionOthers();
                    if (connection == null) {
                        throw new RuntimeException("Brak połączenia z bazą danych");
                    }
                    statement = connection.createStatement();
                    statement.executeUpdate(updateProduct);
                    product.setDodany(true);
                    //showProducts();
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
                            connection.close();
                        } catch (SQLException e) { /* */}
                    }
                }
            }

    public void deleteProduct(ProductBean product) {
        int id = product.getId();
        try {
            String deleteProduct = "DELETE FROM produkty WHERE ID ='"+id+"'";

            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
            }
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
                    connection.close();
                } catch (SQLException e) { /* */}
            }
        }
    }
        public List<ProductBean> searchProduct(int poczatkowyProdukt, int rekordyNaStrone) {
            List<ProductBean> listaProduktowWyszukana = new ArrayList<ProductBean>();
            sumujWartosc();
            sprawdzIlosc();

            try {
                String searchProductQuery = "SELECT * FROM produkty WHERE "+typWyszukiwania+" LIKE '"+wprowadzonaWartosc+"%"+"'"+" LIMIT " + poczatkowyProdukt+ ", " + rekordyNaStrone;

                connection = ConnectionManager.connectionOthers();
                if (connection == null) {
                    throw new RuntimeException("Brak połączenia z bazą danych");
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
            return listaProduktowWyszukana;
        }

        public List<ProductBean> sortProductMethod(int poczatkowyProdukt, int rekordyNaStrone) {
        List<ProductBean> listaProduktowPosortowana = new ArrayList<ProductBean>();
        sumujWartosc();
        sprawdzIlosc();
        String searchSortQuery;

        if(metodaSortowania==1){
            searchSortQuery = "SELECT * FROM produkty WHERE " + typWyszukiwania + " LIKE '" +wprowadzonaWartosc+ "%'"+ "ORDER BY " +typSortowania +" "+descOrAsc+
                    " LIMIT " + poczatkowyProdukt+ ", " + rekordyNaStrone;
            }
        else {
            searchSortQuery = "SELECT * FROM produkty ORDER BY "+typSortowania +" "+descOrAsc+" LIMIT " + poczatkowyProdukt+ ", " + rekordyNaStrone;
            }

        try {
            connection = ConnectionManager.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
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
                    listaProduktowPosortowana.add(produkt);
                }while (resultSet.next());
            }
            resultSet.close();
            statement.close();
            connection.close();
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
        return listaProduktowPosortowana;
    }


    //Gettery, settery klasy ProductDao
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

    public int getMetodaSortowania() {
        return metodaSortowania;
    }
    public void setMetodaSortowania(int metodaSortowania) {
        this.metodaSortowania = metodaSortowania;
    }

    public int getLiczbaRekordow() {
        return liczbaRekordow;
    }
}



