package dao;

import beans.UserBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
    static Connection connection = null;
    static ResultSet resultSet = null;

    public static void preparingDB(){
        Statement statement = null;
        String createDB = "CREATE DATABASE IF NOT EXISTS sklep_elektroniczny";

        String createTable = "CREATE TABLE IF NOT EXISTS " +
                "uzytkownik (ID int(10) NOT NULL AUTO_INCREMENT, Imie varchar(30) NOT NULL, Nazwisko varchar(30) NOT NULL, " +
                "Login varchar(30) NOT NULL, Haslo varchar(30) NOT NULL, Mail varchar(30) NOT NULL, Primary Key(ID))";

        try {
            connection = ConnectionUser.connectionToCreateDB();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            statement.executeUpdate(createDB);
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            connection = ConnectionUser.connectionOthers();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia");
            }
            statement = connection.createStatement();
            statement.executeUpdate(createTable);
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void login(UserBean bean) { //Tworzymy statyczną metodę o nazwie login która przyjmuję jako argument obiekt bean klasy UserBean
        Statement statement = null;
        String login = bean.getLogin();
        String haslo = bean.getHaslo();
        boolean polaWypelnione;
        String searchQuery =
                "SELECT * FROM uzytkownik WHERE Login='"
                        + login
                        + "' AND Haslo='"
                        + haslo
                        + "'";

        if (bean.getLogin().equals("") || bean.getHaslo().equals("")){
            polaWypelnione=false;
        }
        else{
            polaWypelnione=true;
        }

        if(polaWypelnione) {
            preparingDB();
            try {
                //LOGOWANIE
                connection = ConnectionUser.connectionOthers();
                if (connection == null) {
                    throw new RuntimeException("Brak połączenia");
                }
                statement = connection.createStatement();
                resultSet = statement.executeQuery(searchQuery);
                if (resultSet.next()) {
                    //Jeśli użytkownik istnieje ustawia zmienną zalogowany na true
                    if (resultSet.getString("Login").compareTo(bean.getLogin()) == 0 && resultSet.getString("Haslo").compareTo(bean.getHaslo()) == 0) {
                        //System.out.println("CONSOLE: Witaj " + login);
                        bean.setZalogowany(true);
                    }
                    // Jeśli użytkownik nie istnieje ustawia zmienną zalogowany na false
                    else {
                        bean.setZalogowany(false);
                    }
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Błąd przy logowaniu ! " + ex);
            }
        }
    }
}