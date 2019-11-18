package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    static Connection con;


    public static Connection connectionToCreateDB()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return con;
    }

    public static Connection connectionOthers()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sklep_elektroniczny", "root", "");
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return con;
    }

}
