package dao;

import beans.UserBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
    private static Connection connection = null;
    private static ResultSet resultSet = null;
    private static Statement statement = null;
    private static String witaj;


    static void preparingDB(){

        String createDB = "CREATE DATABASE IF NOT EXISTS sklep_elektroniczny";

        try {
            connection = ConnectionManager.connectionToCreateDB();
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

    }
    static void preparingTableUsers(){

        String createTableUsers = "CREATE TABLE IF NOT EXISTS " +
                "uzytkownik (ID int(10) NOT NULL AUTO_INCREMENT, Imie varchar(30) NOT NULL, Nazwisko varchar(30) NOT NULL, " +
                "Login varchar(30) NOT NULL, Haslo varchar(30) NOT NULL, Mail varchar(30) NOT NULL, Primary Key(ID))";
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

    public static void login(UserBean user) { //Tworzymy statyczną metodę o nazwie login która przyjmuję jako argument obiekt bean klasy UserBean

        String login = user.getLogin();
        String haslo = user.getHaslo();
        String searchQuery =
                "SELECT * FROM uzytkownik WHERE Login='"
                        + login
                        + "' AND Haslo='"
                        + haslo
                        + "'";

            preparingDB();
            preparingTableUsers();
            try {
                //LOGOWANIE
                connection = ConnectionManager.connectionOthers();
                if (connection == null) {
                    throw new RuntimeException("Brak połączenia");
                }
                statement = connection.createStatement();
                resultSet = statement.executeQuery(searchQuery);
                if (resultSet.next()) {
                    //Jeśli użytkownik istnieje ustawia zmienną zalogowany na true
                    if (resultSet.getString("Login").compareTo(user.getLogin()) == 0 && resultSet.getString("Haslo").compareTo(user.getHaslo()) == 0) {
                        witaj=user.getLogin();
                        user.setZalogowany(true);
                    }
                    // Jeśli użytkownik nie istnieje ustawia zmienną zalogowany na false
                    else {
                        user.setZalogowany(false);
                    }
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Błąd przy logowaniu ! " + ex);
            }
        }

    public static String getWitaj() {
        return witaj;
    }
    }
