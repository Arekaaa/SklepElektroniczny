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


    public static void preparingDB() {
        String createDB = "CREATE DATABASE IF NOT EXISTS sklep_elektroniczny";

        try {
            connection = ConnectionManager.connectionToCreateDB();
            if (connection == null) {
                throw new RuntimeException("Brak połączenia z bazą danych");
            }
            statement = connection.createStatement();
            statement.executeUpdate(createDB);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Wyjątek związany z błędną składnią SQL");
        } finally {
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
    static void preparingTableUsers(){
        String createTableUsers = "CREATE TABLE IF NOT EXISTS " +
                "uzytkownik (ID int(10) NOT NULL AUTO_INCREMENT, Imie varchar(30) NOT NULL, Nazwisko varchar(30) NOT NULL, " +
                "Login varchar(30) NOT NULL, Haslo varchar(30) NOT NULL, Mail varchar(30) NOT NULL, Primary Key(ID))";
        try {
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
                    throw new RuntimeException("Brak połączenia z bazą danych");
                }
                statement = connection.createStatement();
                resultSet = statement.executeQuery(searchQuery);
                if (resultSet.next()) {
                    if (resultSet.getString("Login").compareTo(user.getLogin()) == 0 && resultSet.getString("Haslo").compareTo(user.getHaslo()) == 0) {
                        witaj=user.getLogin();
                        user.setZalogowany(true);//Jeśli użytkownik istnieje ustawia zmienną zalogowany na true
                    }
                    // Jeśli użytkownik nie istnieje ustawia zmienną zalogowany na false
                    else {
                        user.setZalogowany(false);
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
        }

    public static String getWitaj() {
        return witaj;
    }
    }
