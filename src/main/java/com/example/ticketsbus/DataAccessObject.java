package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public ObservableList<Booking> getBookingData(String query){
        ObservableList<Booking> list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(new Booking(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return list;
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
