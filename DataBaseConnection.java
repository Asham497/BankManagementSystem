package BankManagementSystem;

import java.sql.*;

public class DataBaseConnection
{
    public static Connection getConnection() throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BankManagementSystem";
        String username="root";
        String password="system123";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,username,password);
    }
}