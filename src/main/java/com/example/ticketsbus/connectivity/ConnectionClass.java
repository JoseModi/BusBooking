package com.example.ticketsbus.connectivity;

import java.sql.*;

public class ConnectionClass {
    public Connection connection;
    public Connection getConnection()
    {
        String dbName ="bus";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,username,password);

        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to database", e);
        }
        catch(ClassNotFoundException e) {
            System.out.println("null");
        }
        return connection;
    }
    public void close(Connection connect, PreparedStatement pstmt, ResultSet rs){
        try{
            if(connect != null)
                connect.close();
            if(pstmt != null)
                pstmt.close();
            if(rs!=null)
                rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void close(Connection connect, PreparedStatement pstmt){
        try{
            close(connect, pstmt, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void close(PreparedStatement pstmt){
        try{
            close(null, pstmt, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
