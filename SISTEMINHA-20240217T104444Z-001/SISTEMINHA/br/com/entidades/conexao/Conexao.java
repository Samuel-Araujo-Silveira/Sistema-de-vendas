package com.entidades.conexao;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class Conexao
{
    private static String url="jdbc:mysql://localhost:3306/sisteminha";
    private static String user="root";
    private static String password="1234";
    private static Connection conn;

    public static Connection getConexao()
    {
        if(conn==null)
        {
            try
            {
                conn = DriverManager.getConnection(url,user,password);
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
            return conn;
        }
        else
        {
            return conn;
        }
    }

}