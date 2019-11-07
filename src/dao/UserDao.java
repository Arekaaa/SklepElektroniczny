package dao;

import beans.UserBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    static Connection connection = null;
    static ResultSet resultSet = null;


    public static UserBean login(UserBean bean) { //Tworzymy statyczną metodę o nazwie login typu UserBean która przyjmuję jako argument obiekt bean klasy UserBean

        Statement statement = null;

        String username = bean.getLogin();
        String password = bean.getHaslo();

        String createDB = "CREATE DATABASE IF NOT EXISTS sklep_elektroniczny";

        String createTable = "CREATE table if not EXISTS " +
                "uzytkownik (ID int(10) NOT NULL AUTO_INCREMENT, Imie varchar(30) NOT NULL, Nazwisko varchar(30) NOT NULL, " +
                "Login varchar(30) NOT NULL, Haslo varchar(30) NOT NULL, Mail varchar(30) NOT NULL, Primary Key(ID))";

        String searchQuery =
                "select * from uzytkownik where Login='"
                        + username
                        + "' AND Haslo='"
                        + password
                        + "'";

        try {
            connection = ConnectionUser.connectionToCreateDB();
            if (connection != null) {
                System.out.println("OK. Baza danych istnieje");

            }
            statement = connection.createStatement();
            statement.executeUpdate(createDB);
            statement.close();
            connection.close();
        } catch (SQLException cd) {
            cd.printStackTrace();
        }

        try {
            connection = ConnectionUser.connectionOthers();
            if (connection != null) {
                System.out.println("OK. Tabela istnieje");
            }
            statement = connection.createStatement();
            statement.executeUpdate(createTable);
            statement.close();
            connection.close();
        } catch (SQLException ct) {
            ct.printStackTrace();
        }
        try {
            //Połączenie z DB
            connection = ConnectionUser.connectionOthers();
            if (connection != null) {
                System.out.println("Ok. Przeszukuje tabele");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchQuery);
            while (resultSet.next()) {
                //Jeśli użytkownik istnieje ustawia zmienną zalogowany na true
                if (resultSet.getString("Login").compareTo(bean.getLogin()) == 0 && resultSet.getString("Haslo").compareTo(bean.getHaslo()) == 0) {
                    String imie = resultSet.getString("Imie");
                    String nazwisko = resultSet.getString("Nazwisko");

                    System.out.println("Witaj " + username);
                    //bean.setImie(imie);
                    //bean.setNazwisko(nazwisko);
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

        return bean;
    }
}