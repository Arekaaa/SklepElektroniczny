package dao;

import beans.UserBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDao {
    static Connection connection = null;
    static ResultSet resultSet = null;

    public static void register(UserBean bean) { //Tworzymy statyczną metodę o nazwie register która przyjmuję jako argument obiekt bean klasy UserBean
        Statement statement = null;

        String login = bean.getLogin();
        String haslo = bean.getHaslo();
        String imie = bean.getImie();
        String nazwisko = bean.getNazwisko();
        String mail = bean.getMail();
        String repeatHaslo = bean.getHasloRepeat();
        boolean polaWypelnione;
        boolean haslaNieRozne;
        boolean nieIstnieje=true;

        String insertUser =
                "INSERT INTO uzytkownik VALUES (NULL,'" + imie + "','" + nazwisko + "','" + login + "','" + haslo + "','" + mail + "')";

        String searchQuery =
                "SELECT * FROM uzytkownik WHERE Login='"
                        + login
                        + "' OR Mail='"
                        + mail
                        + "'";

        if (login.equals("") || haslo.equals("") || repeatHaslo.equals("") || imie.equals("") || nazwisko.equals("") || mail.equals("")) {
            polaWypelnione = false;
        } else {
            polaWypelnione = true;
        }

        if(haslo.compareTo(repeatHaslo)==0){
            haslaNieRozne=true;
        }
        else{
            haslaNieRozne=false;
        }

        //Sprawdz czy istnieje juz taki uzytkownik
       if (polaWypelnione && haslaNieRozne) {
           LoginDao.preparingDB();

           try {
               connection = ConnectionUser.connectionOthers();
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
                   connection = ConnectionUser.connectionOthers();
                   if (connection == null) {
                       throw new RuntimeException("Brak połączenia");
                   }
                   statement = connection.createStatement();
                   statement.executeUpdate(insertUser);
                   bean.setZarejestrowany(true);
                   statement.close();
                   connection.close();


               } catch (SQLException ex) {
                   System.out.println("Błąd przy rejestracji ! " + ex);
               }
           }
       }
    }
}
