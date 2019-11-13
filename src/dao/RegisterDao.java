package dao;

import beans.UserBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDao {
    private static Connection connection = null;
    private static ResultSet resultSet = null;
    private static Statement statement = null;
    public static void register(UserBean user) { //Tworzymy statyczną metodę o nazwie register która przyjmuję jako argument obiekt bean klasy UserBean

        String login = user.getLogin();
        String haslo = user.getHaslo();
        String imie = user.getImie();
        String nazwisko = user.getNazwisko();
        String mail = user.getMail();
        boolean nieIstnieje=true;

        String insertUser =
                "INSERT INTO uzytkownik VALUES (NULL,'" + imie + "','" + nazwisko + "','" + login + "','" + haslo + "','" + mail + "')";

        String searchQuery =
                "SELECT * FROM uzytkownik WHERE Login='"
                        + login
                        + "' OR Mail='"
                        + mail
                        + "'";

        //Sprawdz czy istnieje juz taki uzytkownik
           LoginDao.preparingDB();
           LoginDao.preparingTableUsers();
           try {
               connection = ConnectionManager.connectionOthers();
               if (connection == null) {
                   throw new RuntimeException("Brak połączenia");
               }
               statement = connection.createStatement();
               statement.executeQuery(searchQuery);
               resultSet = statement.executeQuery(searchQuery);
               if (resultSet.next()) {
                   if (resultSet.getString("Login").compareTo(login) == 0 || resultSet.getString("Mail").compareTo(mail) == 0) {
                       nieIstnieje = false;
                   }
               }
               resultSet.close();
               statement.close();
               connection.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
           //REJESTRACJA NOWEGO USERA
           if (nieIstnieje) {
               try {
                   connection = ConnectionManager.connectionOthers();
                   if (connection == null) {
                       throw new RuntimeException("Brak połączenia");
                   }
                   statement = connection.createStatement();
                   statement.executeUpdate(insertUser);
                   user.setZarejestrowany(true);
                   statement.close();
                   connection.close();
               } catch (SQLException ex) {
                   System.out.println("Błąd przy rejestracji ! " + ex);
               }
           }
       }
}
