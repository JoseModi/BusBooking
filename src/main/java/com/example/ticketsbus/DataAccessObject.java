package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataAccessObject {
    private ConnectionClass database = new ConnectionClass();
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection connect;
    public DataAccessObject(){

    }

    public void saveData(String query){
        try{
            connect = database.getConnection(); //get connection
            pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            database.close(connect, pstmt, null);
        }
    }


    public int getCount(String checkQuery) {
        int count = 0;
        try {
            connect = database.getConnection(); // get connection
            pstmt = connect.prepareStatement(checkQuery);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close(connect, pstmt, rs);
        }
        return count;
    }
}
