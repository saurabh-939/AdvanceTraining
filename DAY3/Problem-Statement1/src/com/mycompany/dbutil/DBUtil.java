package com.mycompany.dbutil;

import java.sql.*;

public class DBUtil {

 
    public static Connection getConnection()
    {
        Connection conn = null;
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmgmt", "root", "root");
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    
    public static void closeConnection(Connection conn)
    {
        try{
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}